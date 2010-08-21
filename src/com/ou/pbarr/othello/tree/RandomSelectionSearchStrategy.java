package com.ou.pbarr.othello.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A Random Search Strategy for use with {@link Tree}. This makes completely random
 * selections from the start {@link Tree<?>.Node}'s children. The goal has no bearing on 
 * the outcome.
 * @author phil
 */
public class RandomSelectionSearchStrategy<T> extends SearchStrategy<T>
{

	@Override
	public String getName()
	{
		return "Random Search Strategy";
	}

	/**
	 * A list with two nodes is returned, the start node and a random child node.
	 */
	@Override
	public List<Tree<T>.Node> search(Tree<T>.Node start, Heuristic<T> heuristic)
	{
		List<Tree<T>.Node> agenda = new ArrayList<Tree<T>.Node>();
		agenda.add(start);
		List<Tree<T>.Node> children = start.getChildren();
		Collections.shuffle(children);
		agenda.add(children.get(0));
		return agenda;
	}

}
