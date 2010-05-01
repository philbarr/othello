package com.ou.pbarr.othello;

import com.ou.pbarr.othello.controller.Controller;
import com.ou.pbarr.othello.controller.SimpleController;
import com.ou.pbarr.othello.gui.OthelloMainFrame;
import com.ou.pbarr.othello.model.OthelloModel;

public class Main
{
	public static void main(String[] args)
	{
		Controller controller = new SimpleController();
		OthelloMainFrame othelloFrame = new OthelloMainFrame(controller);
		controller.setView(othelloFrame);
		controller.setModel(new OthelloModel());
		othelloFrame.setSize(400,400);
		othelloFrame.setTitle("Othello");
		othelloFrame.setVisible(true);
	}
}
