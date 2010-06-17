package com.ou.pbarr.othello.model;

public class IllegalMoveException extends Exception
{

	public IllegalMoveException()
	{
	}

	public IllegalMoveException(String message)
	{
		super(message);
	}

	public IllegalMoveException(Throwable cause)
	{
		super(cause);
	}

	public IllegalMoveException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
