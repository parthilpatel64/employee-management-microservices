package com.example.demo.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Department;
import com.example.demo.requestdto.DepartmentRequestDto;
import com.example.demo.service.DepartmentService;
import com.example.dto.responsedto.DepartmentResponseDto;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
	
	private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    
    @PostMapping
    public ResponseEntity<DepartmentResponseDto> createDepartment(
            @RequestBody DepartmentRequestDto departmentRequestDto) {

        DepartmentResponseDto savedDepartment =
                departmentService.createDepartment(departmentRequestDto);

        return new ResponseEntity<>(
                savedDepartment,
                HttpStatus.CREATED
        );
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> getDepartmentById(
            @PathVariable Long id) {

       DepartmentResponseDto departmentResponseDto = 
                departmentService.getDepartmentById(id);

       return new ResponseEntity<>(
    		   departmentResponseDto,
               HttpStatus.OK
       );
    }
    
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {

        List<Department> departments =
                departmentService.getAllDepartments();

        return ResponseEntity.ok(departments);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(
            @PathVariable Long id,
            @RequestBody Department department) {

        Department updatedDepartment =
                departmentService.updateDepartment(id, department);

        return ResponseEntity.ok(updatedDepartment);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(
            @PathVariable Long id) {

        departmentService.deleteDepartment(id);

        return ResponseEntity.noContent().build();
    }


}
