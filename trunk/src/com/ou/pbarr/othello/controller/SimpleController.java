package com.ou.pbarr.othello.controller;

import com.ou.pbarr.othello.gui.View;
import com.ou.pbarr.othello.model.Model;

public class SimpleController implements Controller
{
	private View view;
	private Model model;
	
	public void applicationClosing()
	{
		System.out.println("closing");
		System.exit(0);
	}

	public void setView(View view)
	{
		this.view = view;
	}

	public void setModel(Model model)
	{
		this.model = model;
	}
}
