package com.kh.spring.animal;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Animal {
	private String name;
	private int age;

}
