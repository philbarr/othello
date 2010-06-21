package com.ou.pbarr.othello.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import com.ou.pbarr.othello.model.Token.Type;
import com.ou.pbarr.othello.tree.SearchStrategy;
import com.ou.pbarr.othello.tree.Tree;

/**
 * Stores information about the application state, including the state of the board. Player's play as WHITE
 * by default, unless otherwise specified.
 * @author pbarr
 */
public class OthelloModel implements Model
{
	private static final Logger LOG = Logger.getLogger(OthelloModel.class.getName());
	private Token.Type currentPlayerColour = Token.Type.BLACK; // black starts
	private Token.Type playerColour = Token.Type.BLACK; // default to player starts as black
	private OthelloState state = new OthelloState();
	private SearchStrategy currentStrategy;
	private List<SearchStrategy> strategies = new ArrayList<SearchStrategy>();
	private boolean isFinished = false;

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
		return state.getPossibleNextPositions(currentPlayerColour).toArray(new Token[0]);
	}

	@Override
	public void newGame(Type colour)
	{
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
			LOG.severe("Setting up new game failed: " + e.getMessage());
		}
  	this.playerColour = colour;
	}

	@Override
	public void makeMove(int xSquare, int ySquare) throws OutOfOthelloBoardBoundsException, TokenAlreadyExistsInSquareException, IllegalMoveException
	{
		state.playToken(new Token(currentPlayerColour, xSquare, ySquare));
		flipCurrentPlayer();
	}

	@Override
	public Type getCurrentPlayer()
	{
		return currentPlayerColour;
	}

	@Override
	public Type getPlayerColour()
	{
		return playerColour;
	}

	private void flipCurrentPlayer()
	{
		currentPlayerColour = currentPlayerColour == Type.BLACK ? Type.WHITE : Type.BLACK;
	}

	@Override
	public void generateMove()
	{
		OthelloStateExpandable currentState = new OthelloStateExpandable(currentPlayerColour);
		try
		{
			currentState.setTokens(this.getTokens());
			Tree<OthelloStateExpandable> tree = new Tree<OthelloStateExpandable>(currentState);
			tree.setStrategy(getCurrentStrategy());
			Token token = tree.findNextState().getLastCreatedToken();
			LOG.info(getCurrentStrategy().getName() + " played: " + token);
			state.playToken(token);
			flipCurrentPlayer();
		}
		catch (Exception e)
		{
			LOG.severe("Unexpected error generating move: " + e.getMessage());
		}
	}

	@Override
	public boolean gameIsFinished()
	{
		if (!isFinished)
		{
			isFinished = state.getPossibleNextPositions(currentPlayerColour).isEmpty();
		}
		return isFinished;
	}

	@Override
	public int getTokenCountFor(Type type)
	{
		int count = 0;
		for (Token token : state.getTokens())
		{
			if (token.getType() == type)
			{
				count++;
			}
		}
		return count;
	}
}
