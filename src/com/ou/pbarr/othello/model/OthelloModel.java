package com.ou.pbarr.othello.model;

import java.util.ArrayList;
import java.util.List;
import com.ou.pbarr.othello.model.Token.Type;
import com.ou.pbarr.othello.tree.SearchStrategy;

/**
 * Stores information about the application state, including the state of the board. Player's play as WHITE
 * by default, unless otherwise specified.
 * @author pbarr
 */
public class OthelloModel implements Model
{
	private Token.Type playerColour = Token.Type.WHITE;
	private OthelloState state = new OthelloState();
	private SearchStrategy currentStrategy;
	private List<SearchStrategy> strategies = new ArrayList<SearchStrategy>();

	public OthelloModel()
	{
		newGame();
	}
	
	/**
	 * Sets the strategy by name. If the name does not exist in the
	 * current list of strategies, a StrategyDoesNotExistException is thrown.
	 * @throws SearchStrategyDoesNotExistException 
	 */
	@Override
	public void setStrategyByName(String strategyName) throws SearchStrategyDoesNotExistException
	{
		boolean found = false;
		for (SearchStrategy strategy : strategies)
		{
			if (strategyName.equals(strategy.getName()))
			{
				currentStrategy = strategy;
				found = true;
			}
		}
		if (!found)
		{
			throw new SearchStrategyDoesNotExistException(strategyName + "is not a known strategy");
		}
	}

	public SearchStrategy getCurrentStrategy()
	{
		return currentStrategy;
	}

	public void setCurrentStrategy(SearchStrategy currentStrategy)
	{
		this.currentStrategy = currentStrategy;
	}

	@Override
	public String[] getStrategyNames()
	{
		String[] strategyNames = new String[strategies.size()];
		for (int i = 0; i < strategyNames.length; i++)
		{
			strategyNames[i] = strategies.get(i).getName();
		}
		return strategyNames;
	}
	
	@Override
	public void addStrategy(SearchStrategy strategy)
	{
		strategies.add(strategy);
	}

	@Override
	public Token[] getTokens()
	{
		return state.getTokens();
	}

	@Override
	public Token[] getPossibleNextMoves()
	{
		return state.getPossibleNextPositions(playerColour).toArray(new Token[0]);
	}

	@Override
	public void newGame()
	{
		//TODO throw proper exceptions if new game fails, at least log properly
		// set up default board position
  	try
		{
  		state.clearAllTokens();
			state.addToken(new Token(Token.Type.WHITE, 4,4));
			state.addToken(new Token(Token.Type.BLACK, 4,5));
			state.addToken(new Token(Token.Type.BLACK, 5,4));
			state.addToken(new Token(Token.Type.WHITE, 5,5));
		} 
  	catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void setPlayerColour(Type colour)
	{
		this.playerColour = colour;
	}

	@Override
	public void makeMove(int xSquare, int ySquare) throws OutOfOthelloBoardBoundsException, TokenAlreadyExistsInSquareException
	{
		state.playToken(new Token(playerColour, xSquare, ySquare));
	}
}