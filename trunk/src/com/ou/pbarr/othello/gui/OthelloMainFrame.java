package com.ou.pbarr.othello.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import com.ou.pbarr.othello.controller.Controller;
import com.ou.pbarr.othello.controller.SimpleController;

public class OthelloMainFrame extends JFrame implements View
{
	private Controller controller;

	public OthelloMainFrame(Controller controller)
	{
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.controller = controller;
		
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				OthelloMainFrame.this.controller.applicationClosing();
			}
		});
		
	}
}