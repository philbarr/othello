package com.ou.pbarr.othello.gui;


public class ConsoleView implements View
{
	@Override
	public String askForHumanPlayerColour()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void displayError(String error)
	{
		System.err.println(error);
	}

	@Override
	public void setBoardFrozenState(boolean frozen)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setInfoMessage(String string)
	{
		System.out.println(string);
	}

	@Override
	public void updateBoard()
	{
		// TODO Auto-generated method stub

	}

}
