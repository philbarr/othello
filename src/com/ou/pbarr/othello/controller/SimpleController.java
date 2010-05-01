package com.ou.pbarr.othello.controller;

import java.util.logging.Logger;
import com.ou.pbarr.othello.gui.View;
import com.ou.pbarr.othello.model.Model;

public class SimpleController implements Controller
{
	private static final Logger LOG = Logger.getLogger(SimpleController.class.getName());
	private static final String ACTION_NEW = "New";
	private static final String ACTION_LOAD = "Load";
	private static final String ACTION_SAVE = "Save";
	private static final String ACTION_EXIT = "Exit";
	private static final String RADIO_BUTTON_PREFIX = "rdo";
	
	
	private View view;
	private Model model;
	
	public void quit()
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

	@Override
	public void callActionByName(String text)
	{
		LOG.info("called text action: " + text);
		if (ACTION_NEW.equals(text))
		{
			newGame();
		}
		else if (ACTION_LOAD.equals(text))
		{
			load();
		}
		else if (ACTION_SAVE.equals(text))
		{
			save();
		}
		else if (ACTION_EXIT.equals(text))
		{
			quit();
		}
		else if (text != null && text.startsWith(RADIO_BUTTON_PREFIX))
		{
			LOG.info("setting strategy on model:" + text.substring(3));
			model.setStrategy(text.substring(3));
		}
	}

	private void save()
	{
		// TODO Auto-generated method stub
		
	}

	private void load()
	{
		// TODO Auto-generated method stub
		
	}

	private void newGame()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getStrategies()
	{
		return model.getStrategyNames();
	}
}
