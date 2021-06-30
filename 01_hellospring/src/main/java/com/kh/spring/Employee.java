package com.kh.spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	private String name;
	private int salary;
	private double bonus;
	private Department dept;
}
