package com.ou.pbarr.othello.model;

public class OutOfOthelloBoardBoundsException extends Exception
{
	public OutOfOthelloBoardBoundsException()
	{}
	
	public OutOfOthelloBoardBoundsException(String message)
	{
		super(message);
	}
}
