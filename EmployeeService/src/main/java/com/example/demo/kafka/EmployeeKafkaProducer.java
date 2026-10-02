package com.example.demo.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeKafkaProducer {
	
    private static final String TOPIC = "employee-created";

    private final KafkaTemplate<String, EmployeeCreatedEvent> kafkaTemplate;

    public EmployeeKafkaProducer(
            KafkaTemplate<String, EmployeeCreatedEvent> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishEmployeeCreated(EmployeeCreatedEvent event) {

        kafkaTemplate.send(
                TOPIC,
                event.getEmployeeId().toString(),
                event
        );
    }

}
