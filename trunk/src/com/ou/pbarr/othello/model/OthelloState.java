package com.ou.pbarr.othello.model;

public class OthelloState
{
	public enum TokenTypes
	{
		BLACK,
		WHITE
	}

	public void addToken(TokenTypes tokenType, int x, int y) throws OutOfOthelloBoardBoundsException
	{
		if ((x < 1 || x > 8) ||
				(y < 1 || y > 8))
		{
			throw new OutOfOthelloBoardBoundsException("Cannot assign a board token to x: " + "y: " + y);
		}
	}
}
