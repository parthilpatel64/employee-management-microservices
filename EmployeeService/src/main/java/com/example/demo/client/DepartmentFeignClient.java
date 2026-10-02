package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.responseDto.DepartmentResponseDto;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface DepartmentFeignClient {

    @GetMapping("/departments/{id}")
    DepartmentResponseDto getDepartmentById(@PathVariable("id") Long id);
    
    @PostMapping("/departments")
    DepartmentResponseDto saveDepartment();
}
