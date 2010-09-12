package com.ou.pbarr.othello;

import java.util.logging.LogManager;
import com.ou.pbarr.othello.controller.AIController;
import com.ou.pbarr.othello.gui.ConsoleView;
import com.ou.pbarr.othello.model.OthelloModel;
import com.ou.pbarr.othello.model.OthelloStateExpandable;
import com.ou.pbarr.othello.model.Token.Type;
import com.ou.pbarr.othello.tree.DepthFirstSearchStrategy;
import com.ou.pbarr.othello.tree.RandomSelectionSearchStrategy;

public class ConsoleMain
{
	
	public static void main(String[] args)
	{
		LogManager.getLogManager();
		int blackWins = 0;
		int whiteWins = 0;
		
		for (int i = 0; i < 10; i++)
		{
			Type winner = play();
			if (winner == Type.BLACK)
			{
				blackWins++;
			}
			else if (winner == Type.WHITE)
			{
				whiteWins++;
			}
		}
		System.out.printf("\n****************\nBlack: %s\nWhite: %s", blackWins, whiteWins);
	}
	
	public static Type play()
	{
		// set up the model by adding our strategies
		OthelloModel model = new OthelloModel();
		
		// set up the controller and tell it what model to use so that it
		// can affect the data in that model
		AIController controller = new AIController();
		controller.setModel(model);
		
		// set up the view - telling it about the controller to send actions to,
		// and the model to get data from. And tell the controller about the view
		// so that it can act upon it.
		ConsoleView othelloConsole = new ConsoleView();
		controller.setView(othelloConsole);
		controller.setStrategies(new DepthFirstSearchStrategy<OthelloStateExpandable>(), new RandomSelectionSearchStrategy<OthelloStateExpandable>());
		
		// tell the controller to kick things off
		controller.play();		
		
		return model.getWinner();
	}
}
