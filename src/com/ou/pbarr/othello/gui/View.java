package com.ou.pbarr.othello.gui;

public interface View
{
	public static String PLAYER_COLOUR_WHITE = "WHITE";
	public static String PLAYER_COLOUR_BLACK = "BLACK";
	
	// unlikely to need this as the implementation will probably always restrict the user
	// to placing tokens on a board
	public static String ERROR_CANNOT_PLACE_TOKEN_OUT_OF_BOUNDS = "Cannot place token out of bounds";
	public static String ERROR_TOKEN_ALREADY_EXISTS = "Token already exists on this square";
	
	void updateBoard();

	String askForHumanPlayerColour();

	void displayError(String error);

	void setInfoMessage(String string);

}
