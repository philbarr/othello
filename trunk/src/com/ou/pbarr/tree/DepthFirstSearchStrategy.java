package com.ou.pbarr.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A depth first strategy for use with {@link Tree}. The strategy does not record
 * any previous states searched and so is prone to go on searching forever in non
 * finite trees.
 * @author phil
 */
public class DepthFirstSearchStrategy implements SearchStrategy
{
	// the agenda stores the current path being searched
	private List<Tree<?>.Node> agenda = new ArrayList<Tree<?>.Node>();
	
	/**
	 * Searches the tree from start to goal using the depth first technique.
	 * This uses recursion, and is not thread safe.
	 * @param start the node to start from
	 * @param goal the goal node
	 * @return the path to the goal, or an empty List if one was not found.
	 */
	public List<Tree<?>.Node> search(Tree<?>.Node start, Tree<?>.Node goal)
	{
		find(start, goal);
		Collections.reverse(agenda);
		return agenda;
	}
	
	protected Tree<?>.Node find(Tree<?>.Node node, Tree<?>.Node goal)
	{
		// if we are at the goal, add it to the agenda and return it
		if (node.equals(goal))
		{
			agenda.add(node);
			return node;
		}
		
		// for each child, test it to see if it or it's children are the goal
		// if they are, add to the agenda and return, otherwise, return null 
		for (Tree<?>.Node child : node.getChildren())
		{
			Tree<?>.Node found = find(child, goal);
			if (found != null)
			{
				agenda.add(node);
				return node;
			}
		}
		
		return null;
	}
}
