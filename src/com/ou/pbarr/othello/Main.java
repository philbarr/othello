package com.ou.pbarr.othello;

import com.ou.pbarr.othello.controller.Controller;
import com.ou.pbarr.othello.controller.SimpleController;
import com.ou.pbarr.othello.gui.OthelloViewFrame;
import com.ou.pbarr.othello.gui.View;
import com.ou.pbarr.othello.model.OthelloModel;
import com.ou.pbarr.othello.model.OthelloStateExpandable;
import com.ou.pbarr.othello.tree.strategy.DepthFirstSearchStrategy;
import com.ou.pbarr.othello.tree.strategy.RandomSelectionSearchStrategy;

public class Main
{
	public static void main(String[] args)
	{
		// set up the model by adding our strategies
		OthelloModel model = new OthelloModel();
		model.addStrategy(new RandomSelectionSearchStrategy<OthelloStateExpandable>());
		 model.addStrategy(new DepthFirstSearchStrategy<OthelloStateExpandable>());
		
		// set up the controller and tell it what model to use so that it
		// can affect the data in that model
		Controller controller = new SimpleController();
		controller.setModel(model);
		
		// set up the view - telling it about the controller to send actions to,
		// and the model to get data from. And tell the controller about the view
		// so that it can act upon it.
		View othelloFrame = new OthelloViewFrame(controller, model);
		controller.setView(othelloFrame);
		
		// tell the controller to kick things off
		controller.callActionByName(Controller.ACTION_NEW);
	}
}
