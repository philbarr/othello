package com.ou.pbarr.othello;

import com.ou.pbarr.othello.controller.Controller;
import com.ou.pbarr.othello.controller.SimpleController;
import com.ou.pbarr.othello.gui.OthelloMainFrame;
import com.ou.pbarr.othello.gui.View;
import com.ou.pbarr.othello.model.OthelloModel;
import com.ou.pbarr.othello.tree.RandomSelectionSearchStrategy;

public class Main
{
	public static void main(String[] args)
	{
		// set up the model by adding our strategies
		OthelloModel model = new OthelloModel();
		model.addStrategy(new RandomSelectionSearchStrategy());
		// model.addStrategy(new DepthFirstSearchStrategy());
		
		// set up the controller and tell it what model to use so that it
		// can affect the data in that model
		Controller controller = new SimpleController();
		controller.setModel(model);
		
		// set up the view - telling it about the controller to send actions to,
		// and the model to get data from. And tell the controller about the view
		// so that it can act upon it.
		View othelloFrame = new OthelloMainFrame(controller, model);
		controller.setView(othelloFrame);
		
		// tell the controller to kick things off
		controller.callActionByName(Controller.ACTION_NEW);
	}
}
