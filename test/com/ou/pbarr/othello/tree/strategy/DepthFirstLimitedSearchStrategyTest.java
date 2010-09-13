package com.ou.pbarr.othello.tree.strategy;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ou.pbarr.othello.tree.Tree;
import com.ou.pbarr.othello.tree.TreeMaker;
import com.ou.pbarr.othello.tree.Tree.Node;
import com.ou.pbarr.othello.tree.heuristic.Heuristic;

public class DepthFirstLimitedSearchStrategyTest
{
	@Test
	public void testSearch()
	{
		Tree<Integer> tree = new TreeMaker().tree;
		DepthFirstLimitedSearchStrategyMock<Integer> strategy = new DepthFirstLimitedSearchStrategyMock<Integer>(3); 
		tree.setStrategy(strategy);
		
		int expected[] = new int[]{1, 2, 4, 5, 3, 6, 100};
		
		tree.setHeuristic(new Heuristic<Integer>()
		{
			@Override
			public int test(Tree<Integer>.Node node)
			{
				if (node.getState() == 100)
				{
					return 1;
				}
				else
				{
					return 0;
				}
			}
		});
		tree.findNextState();
		
		int actual[] = new int[7];
		for (int i = 0; i < actual.length; i++)
		{
			actual[i] = strategy.verifyRecordedNodes().get(i).getState();
		}
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void testGetName()
	{
		DepthFirstLimitedSearchStrategy<Integer> strategy = new DepthFirstLimitedSearchStrategy<Integer>(5);
		assertEquals("Depth First Limited (5) Strategy", strategy.getName());
	}
}
