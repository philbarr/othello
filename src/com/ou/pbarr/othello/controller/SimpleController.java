package com.ou.pbarr.othello.controller;

import java.util.logging.Logger;
import com.ou.pbarr.othello.gui.View;
import com.ou.pbarr.othello.model.IllegalMoveException;
import com.ou.pbarr.othello.model.Model;
import com.ou.pbarr.othello.model.OutOfOthelloBoardBoundsException;
import com.ou.pbarr.othello.model.SearchStrategyDoesNotExistException;
import com.ou.pbarr.othello.model.TokenAlreadyExistsInSquareException;
import com.ou.pbarr.othello.model.Token.Type;
 
public class SimpleController implements Controller
{
	private static final Logger LOG = Logger.getLogger(SimpleController.class
			.getName());
	private View view;
	private Model model;
	private GameState gameState = GameState.BEFORE_GAME;
	private enum GameState{
		BEFORE_GAME,
		PLAYING,
		FINISHED, 
	}

	public void quit()
	{
		LOG.info("closing");
		System.exit(0);
	}

	public void setView(View view)
	{
		this.view = view;
		view.updateBoard();
	}

	public void setModel(Model model)
	{
		this.model = model;
	}

	@Override
	public void callActionByName(String text)
	{
		LOG.info("called text action: " + text);
		if (ACTION_NEW.equals(text))
		{
			newGame();
		}
		else if (ACTION_LOAD.equals(text))
		{
			load();
		}
		else if (ACTION_SAVE.equals(text))
		{
			save();
		}
		else if (ACTION_EXIT.equals(text))
		{
			quit();
		}
	}

	private void save()
	{
		// TODO Auto-generated method stub

	}

	private void load()
	{
		// TODO Auto-generated method stub

	}

	private void newGame()
	{
		model.newGame(chooseHumanPlayerColour());
		try
		{
			model.setStrategyByName(model.getStrategyNames()[0]);
		}
		catch (SearchStrategyDoesNotExistException e)
		{
			LOG.severe("error setting default strategy: " + e.getMessage());
		}
		view.setBoardFrozenState(false);
		gameState = GameState.PLAYING;
		view.updateBoard();
		view.setInfoMessage("New game. Player is " + model.getPlayerColour());
	}

	@Override
	public String[] getStrategies()
	{
		LOG.info("SimpleController.getStrategies()");
		return model.getStrategyNames();
	}

	@Override
	public Type chooseHumanPlayerColour()
	{
		String colourChoice = view.askForHumanPlayerColour();
		LOG.info("chose human player colour: " + colourChoice);

		if (View.PLAYER_COLOUR_BLACK.equals(colourChoice))
		{
			return Type.BLACK;
		}
		else
		{
			return Type.WHITE;
		}
	}

	@Override
	public void changeStrategyByName(String text)
	{
		try
		{
			model.setStrategyByName(text);
			LOG.info("set strategy on model:" + text);
		}
		catch (SearchStrategyDoesNotExistException e)
		{
			LOG.severe("SearchStrategyDoesNotExistException: " + e.getMessage());
		}
	}

	@Override
	public void selectSquare(int xSquare, int ySquare)
	{
		model.setCurrentlySelectedSquare(xSquare, ySquare);
	}
	
	public void play()
	{

		try
		{
				if (model.getCurrentPlayer() == model.getPlayerColour())
				{
					model.makeMove(model.getCurrentlySelectedSquare().getX(), 
							model.getCurrentlySelectedSquare().getY());
				}
				else
				{
					model.generateMove();
				}
			
			if (!gameIsFinished())
			{
				view.setInfoMessage(model.getCurrentPlayer()
						+ " to play. White tokens: " + model.getTokenCountFor(Type.WHITE)
						+ " - Black tokens: " + model.getTokenCountFor(Type.BLACK)
						+ ". Player is " + model.getPlayerColour());
					view.updateBoard();
			}
		}
		catch (OutOfOthelloBoardBoundsException e)
		{
			view.displayError(View.ERROR_CANNOT_PLACE_TOKEN_OUT_OF_BOUNDS);
		}
		catch (TokenAlreadyExistsInSquareException e)
		{
			view.displayError(View.ERROR_TOKEN_ALREADY_EXISTS);
		}
		catch (IllegalMoveException e)
		{
			// don't do anything, but log the attempt
			LOG.info("Player tried illegal move: " + e.getMessage());
		}
	}

	private boolean gameIsFinished()
	{
		if (gameState == GameState.FINISHED)
		{
			return true;
		}
		if (model.gameIsFinished())
		{
			LOG.info("Game finished");
			view.setBoardFrozenState(true);
			int whiteCount = model.getTokenCountFor(Type.WHITE);
			int blackCount = model.getTokenCountFor(Type.BLACK);

			if (whiteCount > blackCount)
			{
				view.setInfoMessage("White has won!");
			}
			else if (blackCount > whiteCount)
			{
				view.setInfoMessage("Black has won!");
			}
			else if (blackCount == whiteCount)
			{
				view.setInfoMessage("It's a draw!");
			}
			gameState = GameState.FINISHED;
			view.updateBoard();
			return true;
		}
		return false;
	}
}
