package com.ou.pbarr.othello.tree.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ou.pbarr.othello.tree.Tree;
import com.ou.pbarr.othello.tree.Tree.Node;
import com.ou.pbarr.othello.tree.heuristic.Heuristic;

/**
 * A depth first strategy for use with {@link Tree}. The strategy does not record
 * any previous states searched and so is prone to go on searching forever in non
 * finite trees.
 * @author phil
 */
public class DepthFirstSearchStrategy<T> extends SearchStrategy<T>
{
	// the agenda stores the current path being searched
	private List<Tree<T>.Node> agenda = new ArrayList<Tree<T>.Node>();
	private Heuristic<T> heuristic;
	
	/**
	 * Searches the tree from start to goal using the depth first technique.
	 * This uses recursion, and is not thread safe.
	 * @param start the node to start from
	 * @param goal the goal node
	 * @return the path to the goal, or an empty List if one was not found.
	 */
	@Override
	public List<Tree<T>.Node> search(Tree<T>.Node start, Heuristic<T> heuristic)
	{
		agenda = new ArrayList<Tree<T>.Node>();
		this.heuristic = heuristic;
		find(start);
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
	
	protected Tree<T>.Node find(Tree<T>.Node node)
	{
		// if we are at the goal, add it to the agenda and return it
		if (heuristic.test(node) > 0)
		{
			agenda.add(node);
			return node;
		}
		
		// for each child, test it to see if it or it's children are the goal
		// if they are, add to the agenda and return, otherwise, return null 
		for (Tree<T>.Node child : node.getChildren())
		{
			Tree<T>.Node found = find(child);
			if (found != null)
			{
				agenda.add(node);
				return node;
			}
		}
		
		return null;
	}

	@Override
	public String getName()
	{
		return "Depth First Strategy";
	}
}
