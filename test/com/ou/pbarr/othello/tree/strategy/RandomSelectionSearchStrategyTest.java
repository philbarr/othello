package com.ou.pbarr.othello.tree.strategy;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import com.ou.pbarr.othello.tree.Tree;
import com.ou.pbarr.othello.tree.Tree.Node;


public class RandomSelectionSearchStrategyTest
{
	private Tree<String> tree;
	private RandomSelectionSearchStrategy<String> strategy;
	private Tree<String>.Node one;
	private Tree<String>.Node two;
	private Tree<String>.Node three;

	@Before
	public void setUp()
	{
		strategy = new RandomSelectionSearchStrategy<String>();
		tree = new Tree<String>("root");
		one = tree.getRoot().addChild("one");
		two = tree.getRoot().addChild("two");
		three = tree.getRoot().addChild("three");
	}
	
	@Test
	public void testSearch()
	{
		List<Tree<String>.Node> expected = new ArrayList<Tree<String>.Node>();
		expected.add(one);
		expected.add(two);
		expected.add(three);
		List<Tree<String>.Node> actual = strategy.search(tree.getRoot(), (Tree<String>.Node)null);
		assertEquals(2, actual.size());
		assertEquals(tree.getRoot(), actual.get(0));
		assertTrue(expected.contains(actual.get(1)));
	}
	
	@Test
	public void testSearchOneChild()
	{
		List<Tree<String>.Node> expected = new ArrayList<Tree<String>.Node>();
		Tree<String>.Node four = three.addChild("four");
		expected.add(four);
		List<Tree<String>.Node> actual = strategy.search(three, (Tree<String>.Node)null);
		assertEquals(2, actual.size());
		assertEquals(four, actual.get(1));
	}
}
