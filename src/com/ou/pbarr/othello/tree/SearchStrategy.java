package com.ou.pbarr.othello.tree;

import java.util.List;


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
			public boolean test(Tree<T>.Node node)
			{
				return node.equals(goal);
			}
		});
	}

	public abstract String getName();

	public abstract List<Tree<T>.Node> search(Tree<T>.Node root, Heuristic<T> heuristic);
}
