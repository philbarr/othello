package com.ou.pbarr.othello.model;

/**
 * This Exception is thrown when trying to choose a strategy, probably by name, that does
 * not exist in the model.
 * @author phil
 */
public class SearchStrategyDoesNotExistException extends Exception
{
	public SearchStrategyDoesNotExistException(String message)
	{
		super(message);
	}

	public SearchStrategyDoesNotExistException()
	{
	}
}
