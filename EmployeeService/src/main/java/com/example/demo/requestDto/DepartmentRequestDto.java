package com.example.demo.requestDto;

import lombok.Data;

@Data
public class DepartmentRequestDto {
	
	private Long departmentId;
	private String departmentName;
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public DepartmentRequestDto(Long departmentId, String departmentName) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}
	public DepartmentRequestDto() {	}
	
	

}
