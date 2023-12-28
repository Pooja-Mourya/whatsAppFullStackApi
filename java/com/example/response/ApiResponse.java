package com.example.response;

public class ApiResponse<T> {
	private T payload;
	private boolean status;
	private String message;
	private int statusCode;

	public ApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApiResponse(boolean status, String message, T payload, int statusCode) {
		super();
		this.payload = payload;
		this.status = status;
		this.message = message;
		this.statusCode = statusCode;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
