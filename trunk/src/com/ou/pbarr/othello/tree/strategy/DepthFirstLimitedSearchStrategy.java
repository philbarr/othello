package com.ou.pbarr.othello.tree.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ou.pbarr.othello.tree.Tree;
import com.ou.pbarr.othello.tree.heuristic.Heuristic;

public class DepthFirstLimitedSearchStrategy<T> extends SearchStrategy<T>
{
	private int limit;
	// the agenda stores the current path being searched
	private List<Tree<T>.Node> agenda = new ArrayList<Tree<T>.Node>();
	private Heuristic<T> heuristic;

	public DepthFirstLimitedSearchStrategy(int limit)
	{
		this.limit = limit;
	}

	@Override
	public String getName()
	{
		return String.format("Depth First Limited (%s) Strategy", limit);
	}

	@Override
	public List<Tree<T>.Node> search(Tree<T>.Node start, Heuristic<T> heuristic)
	{
		agenda = new ArrayList<Tree<T>.Node>();
		this.heuristic = heuristic;
		find(start, 1);
		if (agenda.size() > 0)
		{
			Collections.reverse(agenda);
		}
		else
		{
			Collections.shuffle(start.getChildren());
			agenda.add(start);
			agenda.add(start.getChildren().get(0));
		}
		return agenda;
	}

	protected Tree<T>.Node find(Tree<T>.Node node, int depth)
	{
		if (depth >= limit)
		{
			return null;
		}
		
		// if we are at the goal, add it to the agenda and return it
		if (heuristic.test(node) > 0)
		{
			agenda.add(node);
			return node;
		}
		
		depth++;
		
		// for each child, test it to see if it or it's children are the goal
		// if they are, add to the agenda and return, otherwise, return null 
		for (Tree<T>.Node child : node.getChildren())
		{
			Tree<T>.Node found = find(child, depth);
			if (found != null)
			{
				agenda.add(node);
				return node;
			}
		}
		
		return null;
	}

}
