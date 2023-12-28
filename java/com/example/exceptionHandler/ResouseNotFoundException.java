package com.example.exceptionHandler;

public class ResouseNotFoundException extends RuntimeException {
	public String resourseName;
	public String fieldName;
	public Long resourseValue;

	public ResouseNotFoundException(String resourseName, String fieldName, Long resourseValue) {
		super(String.format("%s NOT FOUND EXCEPTION %s : %s", fieldName, resourseName, resourseValue));
		this.resourseName = resourseName;
		this.fieldName = fieldName;
		this.resourseValue = resourseValue;
	}

	public String getResourseName() {
		return resourseName;
	}

	public void setResourseName(String resourseName) {
		this.resourseName = resourseName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Long getResourseValue() {
		return resourseValue;
	}

	public void setResourseValue(Long resourseValue) {
		this.resourseValue = resourseValue;
	}
}
