package com.ou.pbarr.othello.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import com.ou.pbarr.othello.model.Token.Type;
import com.ou.pbarr.othello.tree.Expandable;

public class OthelloStateExpandable extends OthelloState implements Expandable
{
	private static final Logger LOG = Logger.getLogger(OthelloStateExpandable.class.getName());
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
	public OthelloStateExpandable[] expand()
	{
		List<OthelloStateExpandable> states = new ArrayList<OthelloStateExpandable>();
		for (Token token : getPossibleNextPositions(typeToExpandFor).toArray(new Token[0]))
		{
			Type otherPlayerToExpandFor = typeToExpandFor == Type.BLACK ? Type.WHITE : Type.BLACK;
			OthelloStateExpandable newState = new OthelloStateExpandable(otherPlayerToExpandFor);
			try
			{
				newState.setTokens(this.getTokens());
				newState.playToken(token);
			}
			catch (Exception e)
			{
				LOG.severe("Unexpected exception expanding state: " + e.getMessage());
			}
			states.add(newState);
		}
		return states.toArray(new OthelloStateExpandable[states.size()]);
	}
	public void setTokens(Token[] tokens) throws TokenAlreadyExistsInSquareException
	{
		board = new Token[OTHELLO_BOARD_SIZE][OTHELLO_BOARD_SIZE];
		for (Token token : tokens)
		{
			addToken(token);
		}
	}

	public Token getLastCreatedToken()
	{
		return lastCreatedToken ;
	}
}
