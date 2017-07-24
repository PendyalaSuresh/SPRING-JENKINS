package com.ericsson.msdp.nim.nulm.exception;

public class NULMException extends Exception {

	public NULMException(ErrorCode errorCode, Exception e) {
		super(errorCode.toString(),e);
	}

	public NULMException(ErrorCode errorCode) {
		super(errorCode.toString());
	}

	public NULMException(ErrorCode errorCode, Throwable e) {
		super(errorCode.toString(),e);
	}

	public enum ErrorCode {
		RDB_DOES_NOT_EXIST,
		FAILED_TO_CREATE_SESSION, 
		OTHER_HIBERNATE_EXCEPTION, OTHER_EXCEPTION
	};

}
