package com.k1687.odiutils;

public class TechnicalException extends RuntimeException {

	public TechnicalException() {
		super();
	}

	public TechnicalException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public TechnicalException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public TechnicalException(String arg0) {
		super(arg0);
	}

	public TechnicalException(Throwable arg0) {
		super(arg0);
	}
}
