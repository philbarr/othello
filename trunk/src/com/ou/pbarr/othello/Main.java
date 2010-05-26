package com.ou.pbarr.othello;

import com.ou.pbarr.othello.controller.Controller;
import com.ou.pbarr.othello.controller.SimpleController;
import com.ou.pbarr.othello.gui.OthelloMainFrame;
import com.ou.pbarr.othello.model.OthelloModel;
import com.ou.pbarr.othello.tree.DepthFirstSearchStrategy;

public class Main
{
	public static void main(String[] args)
	{
		OthelloModel model = new OthelloModel();
		model.addStrategy(new DepthFirstSearchStrategy());
		Controller controller = new SimpleController();
		controller.setModel(model);
		OthelloMainFrame othelloFrame = new OthelloMainFrame(controller);
		controller.setView(othelloFrame);
		othelloFrame.setSize(400,400);
		othelloFrame.setTitle("Othello");
		othelloFrame.setVisible(true);
	}
}
