package com.example.orderservice.service.kafka;

import com.example.orderservice.domain.OrderDetailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateJsonProducer {
    private final KafkaTemplate<String, OrderDetailDTO> kafkaTemplate;
    public void send(String topic, OrderDetailDTO dto) {
        kafkaTemplate.send(topic, dto);
    }
}
