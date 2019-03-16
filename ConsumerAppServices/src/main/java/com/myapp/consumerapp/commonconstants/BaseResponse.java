package com.myapp.consumerapp.commonconstants;

import java.io.Serializable;

public class BaseResponse implements Serializable {

	private static final long serialVersionUID = 3859958120000034510L;

	private int statusCode;
	private String statusMessage;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

}
