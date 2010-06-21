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
	private static final Logger LOG = Logger.getLogger(SimpleController.class.getName());
	private View view;
	private Model model;
	
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
		try
		{
			model.makeMove(xSquare, ySquare);
			view.setInfoMessage(model.getCurrentPlayer() + " to play.");
			view.updateBoard();
			try
			{
				Thread.sleep(500);
			}
			catch (InterruptedException e)
			{
				LOG.info(e.getMessage());
			}
			model.generateMove();
			view.updateBoard();
			view.setInfoMessage(model.getCurrentPlayer() + " to play.");
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
}
