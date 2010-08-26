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
		this(typeToExpandFor, DEFAULT_OTHELLO_BOARD_SIZE);
	}
	
	public OthelloStateExpandable(Type typeToExpandFor, int boardSize)
	{
		super(boardSize);
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
	public void addToken(Token token) throws TokenAlreadyExistsInSquareException, OutOfOthelloBoardBoundsException
	{
		super.addToken(token);
		lastCreatedToken = token;
	}
	
	@Override
	public OthelloStateExpandable[] expand()
	{
		List<OthelloStateExpandable> states = new ArrayList<OthelloStateExpandable>();
		
		List<Token> positions = getPossibleNextPositions(typeToExpandFor);
		if (positions == null || positions.isEmpty())
		{
			typeToExpandFor = typeToExpandFor == Type.BLACK ? Type.WHITE : Type.BLACK;
		}
		positions = getPossibleNextPositions(typeToExpandFor);
		Type newPlayerToExpandFor = typeToExpandFor == Type.BLACK ? Type.WHITE : Type.BLACK;
		
		for (Token token : positions.toArray(new Token[0]))
		{
			OthelloStateExpandable newState = new OthelloStateExpandable(newPlayerToExpandFor, getBoardSize());
			try
			{
				newState.setTokens(this.getTokens());
				newState.playToken(token);
			}
			catch (Exception e)
			{
				LOG.severe("Unexpected exception expanding state: " + e.getMessage());
			}
			LOG.info("expanded state: " + newState);
			states.add(newState);
		}
		return states.toArray(new OthelloStateExpandable[states.size()]);
	}
	public void setTokens(Token[] tokens) throws TokenAlreadyExistsInSquareException, OutOfOthelloBoardBoundsException
	{
		board = new Token[getBoardSize()][getBoardSize()];
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
