package com.ou.pbarr.othello.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import com.ou.pbarr.othello.model.Token.Type;
import com.ou.pbarr.othello.tree.SearchStrategy;
import com.ou.pbarr.othello.tree.Tree;

/**
 * Stores information about the application state, including the state of the
 * board. Player's play as WHITE by default, unless otherwise specified.
 * 
 * @author pbarr
 */
public class OthelloModel implements Model
{
	private static final Logger LOG = Logger.getLogger(OthelloModel.class.getName());
	private Token.Type currentPlayerColour = Token.Type.BLACK; // black starts
	private Token.Type playerColour = Token.Type.BLACK; // default to player starts as black
	private OthelloState state = new OthelloState();
	private SearchStrategy<OthelloStateExpandable> currentStrategy;
	private List<SearchStrategy<OthelloStateExpandable>> strategies = new ArrayList<SearchStrategy<OthelloStateExpandable>>();
	private boolean isFinished = false;

	/**
	 * Sets the strategy by name. If the name does not exist in the current list
	 * of strategies, a StrategyDoesNotExistException is thrown.
	 * 
	 * @throws SearchStrategyDoesNotExistException
	 */
	@Override
	public void setStrategyByName(String strategyName) throws SearchStrategyDoesNotExistException
	{
		boolean found = false;
		for (SearchStrategy<OthelloStateExpandable> strategy : strategies)
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

	public SearchStrategy<OthelloStateExpandable> getCurrentStrategy()
	{
		return currentStrategy;
	}

	public void setCurrentStrategy(SearchStrategy<OthelloStateExpandable> currentStrategy)
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
	public void addStrategy(SearchStrategy<OthelloStateExpandable> strategy)
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
			state.addToken(new Token(Token.Type.WHITE, 4, 4));
			state.addToken(new Token(Token.Type.BLACK, 4, 5));
			state.addToken(new Token(Token.Type.BLACK, 5, 4));
			state.addToken(new Token(Token.Type.WHITE, 5, 5));
			currentPlayerColour = Type.BLACK;
			isFinished = false;
		}
		catch (Exception e)
		{
			LOG.severe("Setting up new game failed: " + e.getMessage());
		}
		this.playerColour = colour;
	}

	@Override
	public void makeMove(int xSquare, int ySquare) throws OutOfOthelloBoardBoundsException,
			TokenAlreadyExistsInSquareException, IllegalMoveException
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
		
		// if there are no possible positions for the new player, flip back
		if (state.getPossibleNextPositions(currentPlayerColour).isEmpty())
		{
			currentPlayerColour = currentPlayerColour == Type.BLACK ? Type.WHITE : Type.BLACK;
		}
	}

	@Override
	public void generateMove() throws TokenAlreadyExistsInSquareException, OutOfOthelloBoardBoundsException, IllegalMoveException
	{
		OthelloStateExpandable currentState = new OthelloStateExpandable(currentPlayerColour);
		currentState.setTokens(this.getTokens());
		Tree<OthelloStateExpandable> tree = new Tree<OthelloStateExpandable>(currentState);
		tree.setStrategy(getCurrentStrategy());
		Token token = tree.findNextState().getLastCreatedToken();
		LOG.info(getCurrentStrategy().getName() + " played: " + token);
		state.playToken(token);
		flipCurrentPlayer();
	}

	@Override
	public boolean gameIsFinished()
	{
		if (!isFinished)
		{
			isFinished = state.getPossibleNextPositions(Type.BLACK).isEmpty() &&
									 state.getPossibleNextPositions(Type.WHITE).isEmpty();
		}
		return isFinished;
	}

	@Override
	public int getTokenCountFor(Type type)
	{
		return state.getTokenCountFor(type);
	}

	@Override
	public int getBoardSquareCount()
	{
		return state.getBoardSize();
	}
}
