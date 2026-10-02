package com.example.demo.model;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "Department")
public class Department {

	@Id
	@Column(name = "dept_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "departmentName")
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Department() {}

}
