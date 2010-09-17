package com.ou.pbarr.othello.gui;

import com.ou.pbarr.othello.model.Model;
import com.ou.pbarr.othello.model.Token.Type;


public class ConsoleView implements View
{
	private Model model;
	
	public ConsoleView(Model model)
	{
		this.model = model;
	}
	
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
		int whiteCount = model.getTokenCountFor(Type.WHITE);
		int blackCount = model.getTokenCountFor(Type.BLACK);
		System.out.println("WHITE: " + whiteCount + "\nBLACK: " + blackCount);
		
	}

}
