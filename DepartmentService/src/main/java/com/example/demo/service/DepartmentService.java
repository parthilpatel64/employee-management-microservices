package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.requestdto.DepartmentRequestDto;
import com.example.dto.responsedto.DepartmentResponseDto;

@Service
public class DepartmentService {
	
	private final DepartmentRepository departmentRepository;

	public DepartmentService(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}
	
	public DepartmentResponseDto createDepartment(DepartmentRequestDto departmentRequestDto) {
		Department department = new Department();
		department.setId(departmentRequestDto.getId());
		department.setName(departmentRequestDto.getName());
		
		department =  departmentRepository.save(department);
		
		DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
		departmentResponseDto.setDepartmentId(department.getId());
		departmentResponseDto.setDepartmentName(department.getName());
		
		return departmentResponseDto;
	}
	
	 public DepartmentResponseDto getDepartmentById(Long id) {
		 
		 Department department = departmentRepository.findById(id)
         .orElseThrow(() ->
                 new RuntimeException("Department not found with id: " + id));
		 
		 DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
			departmentResponseDto.setDepartmentId(department.getId());
			departmentResponseDto.setDepartmentName(department.getName());
			
			return departmentResponseDto;
	       
	    }
	 
	  public List<Department> getAllDepartments() {
	        return departmentRepository.findAll();
	    }

	    // Update Department
	    public Department updateDepartment(Long id, Department department) {

	        Department existingDepartment = departmentRepository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("Department not found with id: " + id));

	        existingDepartment.setName(department.getName());

	        return departmentRepository.save(existingDepartment);
	    }
	    
	    public void deleteDepartment(Long id) {

	        Department existingDepartment = departmentRepository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("Department not found with id: " + id));

	        departmentRepository.delete(existingDepartment);
	    }

}
