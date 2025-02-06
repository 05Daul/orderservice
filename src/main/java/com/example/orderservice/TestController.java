package com.example.orderservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Slf4j
public class TestController {
    @GetMapping("/service")
    public String test() {
        log.info("test---service");
        return "test === service";
    }


    @GetMapping("/getdata")
    public OrderDTO getdata() {
        OrderDTO dto = new OrderDTO("bts","방탄","서울",1000);
        return dto;
    }
    @GetMapping("/test2")
    public ResponseEntity<String> test2(@RequestHeader(value = "X-From-Gateway", required = false) String fromGateway) {
        if ("true".equals(fromGateway)) {
            return ResponseEntity.ok("This request came through the gateway123.");
        }
        return ResponseEntity.ok("This request came directly to the backend.");
    }

}
