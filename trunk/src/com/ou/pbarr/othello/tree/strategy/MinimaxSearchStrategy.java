package com.ou.pbarr.othello.tree.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.ou.pbarr.othello.tree.Tree;
import com.ou.pbarr.othello.tree.heuristic.Heuristic;

public class MinimaxSearchStrategy<T> extends SearchStrategy<T>
{
	private int limit;
	// the agenda stores the current path being searched
	private List<Tree<T>.Node> agenda = new ArrayList<Tree<T>.Node>();
	private Heuristic<T> heuristic;

	public MinimaxSearchStrategy(int limit)
	{
		this.limit = limit;
	}

	@Override
	public String getName()
	{
		return String.format("Minimax (%s) Strategy", limit);
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
			return node;
		}
		
		
		if (!node.hasChildren())
		{
			return node;
		}
		
		depth++;
		
		List<Tree<T>.Node> children = node.getChildren();
		Tree<T>.Node min = children.get(0);
		Tree<T>.Node max = children.get(0);
		
		for (Tree<T>.Node child : children)
		{
			
			
			if (heuristic.test(find(child, depth)) > heuristic.test(max))
			{
				max = child;
			}
			if (heuristic.test(find(child, depth)) < heuristic.test(min))
			{
				min = child;
			}
		}
		
		//get all the children, sort by their heuristic value, and return the MIN or MAX
		//depending on where we are in the depth
		if (depth % 2 == 0) // opposing player, get MIN
		{
			return min;
		}
		else // this player, get MAX
		{
			return max;
		}
	}
}
