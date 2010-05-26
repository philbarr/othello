package com.ou.pbarr.othello.model;

import java.util.ArrayList;
import java.util.List;
import com.ou.pbarr.othello.tree.SearchStrategy;

public class OthelloModel implements Model
{
	private SearchStrategy currentStrategy;
	private List<SearchStrategy> strategies = new ArrayList<SearchStrategy>();

	@Override
	public void setStrategyByName(String strategyName)
	{
		for (SearchStrategy strategy : strategies)
		{
			if (strategyName.equals(strategy.getName()))
			{
				currentStrategy = strategy;
			}
		}
	}

	public SearchStrategy getCurrentStrategy()
	{
		return currentStrategy;
	}

	public void setCurrentStrategy(SearchStrategy currentStrategy)
	{
		this.currentStrategy = currentStrategy;
	}

	@Override
	public String[] getStrategyNames()
	{
		String[] strategyNames = new String[strategies.size()];
		for (int i = 0; i < strategyNames.length; i++)
		{
			strategyNames[i] = strategies.get(i).getName();
		}
		return strategyNames;
	}
	
	@Override
	public void addStrategy(SearchStrategy strategy)
	{
		strategies.add(strategy);
	}
}
