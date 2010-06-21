package com.ou.pbarr.othello.model;

import com.ou.pbarr.othello.model.Token.Type;
import com.ou.pbarr.othello.tree.Expandable;

public class OthelloStateExpandable extends OthelloState implements Expandable
{
	private Token lastCreatedToken = null;
	private Type typeToExpandFor;
	
	public OthelloStateExpandable(Type typeToExpandFor)
	{
		this.typeToExpandFor = typeToExpandFor;
	}
	
	@Override
	public void playToken(Token token)
			throws TokenAlreadyExistsInSquareException,
			OutOfOthelloBoardBoundsException, IllegalMoveException
	{
		super.playToken(token);
		lastCreatedToken = token;
	}
	
	@Override
	public void addToken(Token token) throws TokenAlreadyExistsInSquareException
	{
		super.addToken(token);
		lastCreatedToken = token;
	}
	
	@Override
	public Token[] expand()
	{
		for (Token token : getPossibleNextPositions(typeToExpandFor).toArray(new Token[0]))
		{
			
		}
		return null;
	}
	public Token getLastCreatedToken()
	{
		return lastCreatedToken ;
	}
}
