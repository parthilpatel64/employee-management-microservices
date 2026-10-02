package com.example.demo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeKafkaConsumer {

	   @KafkaListener(
	            topics = "employee-created",
	            groupId = "department-service-group"
	    )
	    public void consumeEmployeeCreated(EmployeeCreatedEvent event) {

	        System.out.println("=================================");
	        System.out.println("Employee Created Event Received");
	        System.out.println("Employee ID   : " + event.getEmployeeId());
	        System.out.println("Employee Name : " + event.getName());
	        System.out.println("Email         : " + event.getEmail());
	        System.out.println("Department ID : " + event.getDepartmentId());
	        System.out.println("=================================");
	    }
}
