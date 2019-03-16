package com.myapp.consumerapp.customeexception;

public class MyAppException extends Exception {

	private static final long serialVersionUID = -3965140316789517795L;

	public MyAppException() {

	}

	public MyAppException(String message) {
		super(message);
	}

	public MyAppException(Throwable e) {
		super(e);
	}

	public MyAppException(String message, Throwable e) {
		super(message, e);
	}

}
