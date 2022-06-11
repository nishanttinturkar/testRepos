package com.cg.Exceptions;

public class InvalidEmployeeDataException extends Exception{
	public InvalidEmployeeDataException(String message) {
		super(message);
	}
}