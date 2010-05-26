package com.ou.pbarr.othello.model;

import java.util.ArrayList;
import java.util.List;

public class OthelloState
{
	private List<Token> tokens = new ArrayList<Token>();

	public void addToken(Token token) throws OutOfOthelloBoardBoundsException
	{
		tokens.add(token);
	}

	public List<Token> getPossibleNextPositions()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
