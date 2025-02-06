FROM gradle:8.11.1-jdk17 as build
# 소스 코드를 복사할 작업디렉토리를 생성
WORKDIR /mpapp
# 호스트 머신에 소스코드를 이미지작업 디렉토리로 복사
COPY . /myapp

# 이전 빌드에서 생성된 모든 build/ 디렉토리 내용을 삭제, 새롭게 빌드
# 프로젝트를 빌드
#gradle 은 설치돼 있는 gradle을 이용해서 빌드, gradlew는 프로젝트를 포함된 gradle을 이용
# CICD 에서는 gradlew 를 이용해서 작업
# -x test -> test를 제와하고 작업
# gradlew 를 실행 할 수 있는 권한 부여

COPY gradle /myapp/gradle
COPY gradlew /myapp/
COPY build.gradle settings.gradle /myapp/
## 추가 이부분은 gradle 종속성을 먼저 복사해서 캐싱


RUN ./gradlew dependencies --no-daemon
#추가2 종속성 필요한게 있으면 다운

RUN chmod +x gradlew
RUN ./gradlew clean build --no-daemon -x test
# no 데몬이라는 것은 데몬을 이용치 않고 이를 빌드 하겠다는 의미를 말한다.
# 여기까지는 gradle 빌드를 의마.

# 이제는 자바를 실행하기 위한 빌드 ing
FROM openjdk:17-alpine
WORKDIR /myapp
#프로젝트 빌드로 생성된 jar파일을 런타임 이미지로 복사
COPY --from=build /myapp/build/*.jar /myapp/orderservice.jar
EXPOSE 9200
ENTRYPOINT ["java", "-jar", "orderservice.jar"]

