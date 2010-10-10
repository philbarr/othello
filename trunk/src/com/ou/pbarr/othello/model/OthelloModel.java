package com.ou.pbarr.othello.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import com.ou.pbarr.othello.model.Token.Type;
import com.ou.pbarr.othello.tree.Tree;
import com.ou.pbarr.othello.tree.heuristic.Heuristic;
import com.ou.pbarr.othello.tree.heuristic.SumValueHeuristic;
import com.ou.pbarr.othello.tree.strategy.MinimaxSearchStrategy;
import com.ou.pbarr.othello.tree.strategy.SearchStrategy;

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
	private SearchStrategy<OthelloStateExpandable> blackStrategy;
	private SearchStrategy<OthelloStateExpandable> whiteStrategy;
	private List<SearchStrategy<OthelloStateExpandable>> strategies = new ArrayList<SearchStrategy<OthelloStateExpandable>>();
	private boolean isFinished = false;
	private Token currentlySelectedSquare = null;

	/**
	 * Sets the strategy by name. If the name does not exist in the current list
	 * of strategies, a StrategyDoesNotExistException is thrown.
	 * 
	 * @throws SearchStrategyDoesNotExistException
	 */
	@Override
	public void setStrategyByName(Type type, String strategyName) throws SearchStrategyDoesNotExistException
	{
		boolean found = false;
		for (SearchStrategy<OthelloStateExpandable> strategy : strategies)
		{
			if (strategyName.equals(strategy.getName()))
			{
				if (type == Type.BLACK)
				{
					blackStrategy = strategy;
				}
				else
				{
					whiteStrategy = strategy;
				}
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
		if (currentPlayerColour == Type.BLACK)
		{
			 return blackStrategy;
		}
		else
		{
			return whiteStrategy;
		}
	}

	public void setCurrentStrategy(Type type, SearchStrategy<OthelloStateExpandable> strategy)
	{
		if (type == Type.BLACK)
		{
			blackStrategy = strategy;
		}
		else
		{
			whiteStrategy = strategy;
		}
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
		currentPlayerColour = state.getNextLegalPlayer(currentPlayerColour);
	}

	@Override
	public void generateMove() throws TokenAlreadyExistsInSquareException, OutOfOthelloBoardBoundsException, IllegalMoveException
	{
		OthelloStateExpandable currentState = new OthelloStateExpandable(currentPlayerColour);
		currentState.setTokens(this.getTokens());
		Tree<OthelloStateExpandable> tree = new Tree<OthelloStateExpandable>(currentState);
		tree.setStrategy(getCurrentStrategy());
		tree.setHeuristic(getHeuristic());
		Token token = tree.findNextState().getLastCreatedToken();
		LOG.info(getCurrentStrategy().getName() + " played: " + token);
		state.playToken(token);
		flipCurrentPlayer();
	}

	//TODO pluggable heuristic
	public Heuristic<OthelloStateExpandable> getHeuristic()
	{
		if (getCurrentStrategy() instanceof MinimaxSearchStrategy<?>)
		{
			return new SumValueHeuristic();
		}
		
		return new Heuristic<OthelloStateExpandable>(){

			@Override
			public int test(Tree<OthelloStateExpandable>.Node node)
			{
				OthelloStateExpandable state = node.getState();
				boolean noMovesForBlack = state.getPossibleNextPositions(Type.BLACK).isEmpty();
				boolean noMovesForWhite = state.getPossibleNextPositions(Type.WHITE).isEmpty();
				Type otherColour = currentPlayerColour == Type.BLACK ? Type.WHITE : Type.BLACK;
				boolean currentColourWins = state.getTokenCountFor(currentPlayerColour) > state.getTokenCountFor(otherColour);
				
				if (noMovesForBlack &&	noMovesForWhite && currentColourWins)
				{
					return 1;
				}
				return 0;
			}};
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

	@Override
	public Token getCurrentlySelectedSquare()
	{
		return currentlySelectedSquare;
	}

	@Override
	public void setCurrentlySelectedSquare(int xSquare, int ySquare)
	{
		this.currentlySelectedSquare  = new Token(null, xSquare, ySquare);
	}

	@Override
	public Type getWinner()
	{
		int whiteCount = getTokenCountFor(Type.WHITE);
		int blackCount = getTokenCountFor(Type.BLACK);
		if (whiteCount > blackCount)
		{
			return Type.WHITE;
		}
		else if (blackCount > whiteCount)
		{
			return Type.BLACK;
		}
		return null;
	}
}
