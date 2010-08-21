package com.ou.pbarr.othello.tree;

import java.util.ArrayList;
import java.util.List;

public class BreadthFirstSearchStrategy<T> extends SearchStrategy<T>
{
	private List<Tree<T>.Node> agenda = new ArrayList<Tree<T>.Node>();
	private Heuristic<T> heuristic;

	
	public List<Tree<T>.Node> search(Tree<T>.Node start, Heuristic<T> heuristic)
	{
		this.heuristic = heuristic;
		List<Tree<T>.Node> parents = new ArrayList<Tree<T>.Node>();
		parents.add(start);
		find(parents);
		return agenda;
	}
	
	Tree<T>.Node find(List<Tree<T>.Node> parents)
	{
		System.out.println(parents);
		List<Tree<T>.Node> newParents = new ArrayList<Tree<T>.Node>();
		
		for (Tree<T>.Node parent : parents)
		{
			
			for (Tree<T>.Node child : parent.getChildren())
			{
				if (heuristic.test(child))
				{
					agenda.add(child);
					return parent;
				}
				newParents.add(child);
			}
		}
		
		Tree<T>.Node found = find(newParents);
		if (found != null)
		{
			agenda.add(found);
		}
		
	
		return null;
	}

	@Override
	public String getName()
	{
		return "Breadth First Strategy";
	}
	
	
}
