package com.ou.pbarr.othello.model;

import com.ou.pbarr.othello.tree.SearchStrategy;

public class OthelloModel implements Model
{
	private SearchStrategy currentStrategy;
	private SearchStrategy[] strategies;

	@Override
	public void setStrategy(String strategy)
	{
		for (int i = 0; i < strategies.length; i++)
		{
			if (strategy.equals(strategies[i].getName()))
			{
				currentStrategy = strategies[i];
			}
		}
	}

	@Override
	public String[] getStrategyNames()
	{
		String[] strategyNames = new String[strategies.length];

		for (int i = 0; i < strategies.length; i++)
		{
			strategyNames[i] = strategies[i].getName();
		}

		return strategyNames;
	}

}
