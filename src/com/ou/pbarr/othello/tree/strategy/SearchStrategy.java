package com.ou.pbarr.othello.tree.strategy;

import java.util.List;

import com.ou.pbarr.othello.tree.Tree;
import com.ou.pbarr.othello.tree.Tree.Node;
import com.ou.pbarr.othello.tree.heuristic.Heuristic;


public abstract class SearchStrategy<T>
{
	/**
	 * 
	 * @param agenda
	 * @return the path to the found node, or null if no path was found
	 */
	public List<Tree<T>.Node> search(final Tree<T>.Node start, final Tree<T>.Node goal)
	{
		return search(start, new Heuristic<T>(){

			@Override
			public int test(Tree<T>.Node node)
			{
				if (node.equals(goal))
				{
					return 1;
				}
				else
				{
					return 0;
				}
			}
		});
			
	}

	public abstract String getName();

	public abstract List<Tree<T>.Node> search(Tree<T>.Node root, Heuristic<T> heuristic);
}
