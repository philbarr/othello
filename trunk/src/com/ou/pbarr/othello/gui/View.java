package com.ou.pbarr.othello.gui;

public interface View
{
	public static String PLAYER_COLOUR_WHITE = "WHITE";
	public static String PLAYER_COLOUR_BLACK = "BLACK";
	
	void updateBoard();

	String askForHumanPlayerColour();

}
