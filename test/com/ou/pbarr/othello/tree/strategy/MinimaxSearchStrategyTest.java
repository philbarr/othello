package com.ou.pbarr.othello.tree.strategy;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.ou.pbarr.othello.tree.Tree;
import com.ou.pbarr.othello.tree.heuristic.Heuristic;

public class MinimaxSearchStrategyTest
{
	Tree<Integer> tree = new Tree<Integer>(4);
	
	@Before
	/*
	 * Creates a tree like
	 *              4                   
	 *      -5               4          MAX lev 1
	 *   10   -5        6       4       MIN lev 2
	 * 9  10 -5 -9    6  3    4   1     MAX lev 3
	 */
	public void setUp()
	{
		Tree<Integer>.Node n1a = tree.getRoot().addChild(-5);
		Tree<Integer>.Node n1b = tree.getRoot().addChild(4);
		Tree<Integer>.Node n2a = n1a.addChild(10);
		Tree<Integer>.Node n2b = n1a.addChild(-5);
		Tree<Integer>.Node n2c = n1b.addChild(6);
		Tree<Integer>.Node n2d = n1b.addChild(4);
		n2a.addChild(9);
		n2a.addChild(10);
		n2b.addChild(-5);
		n2b.addChild(-9);
		n2c.addChild(6);
		n2c.addChild(3);
		n2d.addChild(4);
		n2d.addChild(1);
		
	}
	
	@Test
	public void testMinimax()
	{
		MinimaxSearchStrategy<Integer> mss = new MinimaxSearchStrategy<Integer>(3);
		List<Tree<Integer>.Node> actual = mss.search(tree.getRoot(), new Heuristic<Integer>()
		{
			@Override
			public int test(Tree<Integer>.Node node)
			{
				return node.getState();
			}
		});
		assertEquals(new Integer(4), actual.get(0).getState());
		assertEquals(new Integer(4), actual.get(1).getState());
	}
}
