package com.ou.pbarr.othello.model;

/**
 * This is thrown if a token is added to a square on the board and a token
 * already exists in that place
 * @author phil
 */
public class TokenAlreadyExistsInSquareException extends Exception
{
	public TokenAlreadyExistsInSquareException()
	{
	}

	public TokenAlreadyExistsInSquareException(String message)
	{
		super(message);
	}
}
