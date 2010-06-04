package com.ou.pbarr.othello.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * A Random Search Strategy for use with {@link Tree}. This makes completely random
 * selections from the start {@link Tree<?>.Node}'s children. The goal has no bearing on 
 * the outcome.
 * @author phil
 */
public class RandomSelectionSearchStrategy implements SearchStrategy
{

	private Random random = new Random();
	
	@Override
	public String getName()
	{
		return "Random Search Strategy";
	}

	/**
	 * A list with two nodes is returned, the start node and a random child node.
	 */
	@Override
	public List<Tree<?>.Node> search(Tree<?>.Node start, Tree<?>.Node goal)
	{
		List<Tree<?>.Node> agenda = new ArrayList<Tree<?>.Node>();
		agenda.add(start);
		Collections.shuffle(start.getChildren());
		agenda.add(start.getChild(random.nextInt(start.getChildren().size() -1)));
		return agenda;
	}

}
