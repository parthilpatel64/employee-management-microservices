package com.example.demo.responseDto;

import lombok.Data;

@Data
public class DepartmentResponseDto {
	
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
	public DepartmentResponseDto(Long departmentId, String departmentName) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}
	
	public DepartmentResponseDto() {}
	@Override
	public String toString() {
		return "DepartmentResponseDto [departmentId=" + departmentId + ", departmentName=" + departmentName + "]";
	}

}
