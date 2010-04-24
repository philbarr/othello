package com.ou.pbarr.tree;

import java.util.List;


public interface Strategy
{
	/**
	 * 
	 * @param agenda
	 * @return the path to the found node, or null if no path was found
	 */
	List<Tree<?>.Node> search(Tree<?>.Node start, Tree<?>.Node goal);
}
