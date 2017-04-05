package com.training.spring.model;

public enum TestMedium {
	INPERSON("In Person"), SKYPE("Skype");
	
	private String string="";
	
	private TestMedium(String value){
		this.string = value;
	}
	
	public String toString(){
		return this.string;
	}
}