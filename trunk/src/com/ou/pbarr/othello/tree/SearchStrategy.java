package com.ou.pbarr.othello.tree;

import java.util.List;


public interface SearchStrategy
{
	/**
	 * 
	 * @param agenda
	 * @return the path to the found node, or null if no path was found
	 */
	List<Tree<?>.Node> search(Tree<?>.Node start, Tree<?>.Node goal);

	String getName();
}
