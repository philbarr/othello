package com.ou.pbarr.othello.controller;

import com.ou.pbarr.othello.gui.View;
import com.ou.pbarr.othello.model.Model;
import com.ou.pbarr.othello.model.OthelloStateExpandable;
import com.ou.pbarr.othello.model.Token.Type;
import com.ou.pbarr.othello.tree.strategy.SearchStrategy;

public class AIController implements Controller
{

	private Model model;
	private View view;
	private SearchStrategy<OthelloStateExpandable> blackStrategy;
	private SearchStrategy<OthelloStateExpandable> whiteStrategy;
	
	public void setStrategies(SearchStrategy<OthelloStateExpandable> blackStrategy, SearchStrategy<OthelloStateExpandable> whiteStrategy)
	{
		this.blackStrategy = blackStrategy;
		this.whiteStrategy = whiteStrategy;
	}

	@Override
	public void callActionByName(String text)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeStrategyByName(String text)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Type chooseHumanPlayerColour()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getStrategies()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void play()
	{
		
		model.addStrategy(blackStrategy);
		model.addStrategy(whiteStrategy);
		try
		{
			model.setStrategyByName(Type.BLACK, blackStrategy.getName());
			model.setStrategyByName(Type.WHITE, whiteStrategy.getName());
			model.newGame(Type.BLACK);
			while(!model.gameIsFinished())
			{
				model.generateMove();
			}
			int whiteCount = model.getTokenCountFor(Type.WHITE);
			int blackCount = model.getTokenCountFor(Type.BLACK);
			String winner = "DRAW";
			if (whiteCount > blackCount)
			{
				winner = "WHITE";
			}
			else if (blackCount > whiteCount)
			{
				winner = "BLACK";
			}
			view.setInfoMessage("Game finished: \nWHITE: " + whiteCount + "\nBLACK: " + blackCount + "\nWinner: " + winner);			
			quit();
		}
		catch (Exception e)
		{
			view.displayError("Failed: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public void quit()
	{
	}

	@Override
	public void selectSquare(int xSquare, int ySquare)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setModel(Model model)
	{
		this.model = model;
	}

	@Override
	public void setView(View view)
	{
		this.view = view;
	}

}
