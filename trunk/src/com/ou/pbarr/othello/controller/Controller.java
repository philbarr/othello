package com.ou.pbarr.othello.controller;

import com.ou.pbarr.othello.gui.View;
import com.ou.pbarr.othello.model.Model;

public interface Controller
{
	public static final String ACTION_NEW = "New";
	public static final String ACTION_LOAD = "Load";
	public static final String ACTION_SAVE = "Save";
	public static final String ACTION_EXIT = "Exit";

	void quit();

	void setView(View view);

	void setModel(Model model);

	void callActionByName(String text);

	String[] getStrategies();

	void chooseHumanPlayerColour();

	void changeStrategyByName(String text);

	void selectSquare(int xSquare, int ySquare);

}
