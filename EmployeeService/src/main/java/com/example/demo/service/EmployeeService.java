package com.example.demo.service;

import com.example.demo.client.DepartmentFeignClient;
import com.example.demo.kafka.EmployeeCreatedEvent;
import com.example.demo.kafka.EmployeeKafkaProducer;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.requestDto.EmployeeRequestDto;
import com.example.demo.responseDto.DepartmentResponseDto;
import com.example.demo.responseDto.EmployeeResponseDto;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentFeignClient departmentFeignClient;
    private final EmployeeKafkaProducer kafkaProducer;


   
    public EmployeeService(EmployeeRepository employeeRepository, DepartmentFeignClient departmentFeignClient,
			EmployeeKafkaProducer employeeKafkaProducer) {
		this.employeeRepository = employeeRepository;
		this.departmentFeignClient = departmentFeignClient;
		this.kafkaProducer = employeeKafkaProducer;
	}



	// Create Employee
    @CircuitBreaker(
            name = "departmentService",
            fallbackMethod = "departmentFallback"
    )
    @Retry(name = "departmentService")
    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto) {
    	EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();

    	DepartmentResponseDto departmentResponseDto =  departmentFeignClient.getDepartmentById(employeeRequestDto.getDepartmentId());
    	if((departmentResponseDto.getDepartmentId()==null) && (departmentResponseDto.getDepartmentId()!=0L) ) {
    		return employeeResponseDto;
    	}
    	
    	Employee employee = new Employee();
    	employee.setId(employeeRequestDto.getId());
    	employee.setName(employeeRequestDto.getName());
    	employee.setEmail(employeeRequestDto.getEmail());
    	employee.setPhone(employeeRequestDto.getPhone());
    	employee.setSalary(employeeRequestDto.getSalary());
    	employee.setStatus(employeeRequestDto.getStatus());
    	employee.setDepartmentId(employeeRequestDto.getDepartmentId());
    	
    	employee  = employeeRepository.save(employee);
    	
    	 EmployeeCreatedEvent event = new EmployeeCreatedEvent(
    			 employee.getId(),
    			 employee.getName(),
    			 employee.getEmail(),
    			 employee.getDepartmentId()
         );

         // 3. Publish event to Kafka
    	kafkaProducer.publishEmployeeCreated(event);
   
   
    	employeeResponseDto.setId(employee.getId());
    	employeeResponseDto.setName(employee.getName());
    	employeeResponseDto.setEmail(employee.getEmail());
    	employeeResponseDto.setPhone(employee.getPhone());
    	employeeResponseDto.setSalary(employee.getSalary());
    	employeeResponseDto.setStatus(employee.getStatus());
    	employeeResponseDto.setDepartmentId(employee.getDepartmentId());
    	
        return employeeResponseDto;
    }

    public DepartmentResponseDto departmentFallback(
            Long departmentId,
            Throwable ex) {

        System.out.println(
                "Department Service unavailable: "
                + ex.getMessage()
        );

        return null;
    }
    
    // Get Employee by ID
    public Employee getEmployeeById(Long id) {

        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found with id: " + id));
    }

    // Get All Employees
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

    // Update Employee
    public Employee updateEmployee(Long id, Employee employee) {

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found with id: " + id));

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhone(employee.getPhone());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setDepartmentId(employee.getDepartmentId());
        existingEmployee.setStatus(employee.getStatus());

        return employeeRepository.save(existingEmployee);
    }

    // Delete Employee
    public void deleteEmployee(Long id) {

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found with id: " + id));

        employeeRepository.delete(existingEmployee);
    }
}