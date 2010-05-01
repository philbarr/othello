package com.ou.pbarr.othello.controller;

import com.ou.pbarr.othello.gui.View;
import com.ou.pbarr.othello.model.Model;

public interface Controller
{

	void quit();

	void setView(View view);

	void setModel(Model model);

	void callActionByName(String text);

	String[] getStrategies();

}
