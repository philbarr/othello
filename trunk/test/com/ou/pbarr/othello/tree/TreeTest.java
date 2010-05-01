package com.ou.pbarr.othello.tree;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.ou.pbarr.othello.tree.BreadthFirstSearchStrategy;
import com.ou.pbarr.othello.tree.DepthFirstSearchStrategy;
import com.ou.pbarr.othello.tree.Tree;

public class TreeTest
{
	private TreeMaker maker;
	

	@Before
	public void setUp()
	{
		maker = new TreeMaker();
	}
	@Test
	public void testDepthFirstStrategy()
	{
		DepthFirstSearchStrategy dfs = new DepthFirstSearchStrategy();
		
		List<Tree<Integer>.Node> expected = new ArrayList<Tree<Integer>.Node>();
		expected.add(maker.tree.getRoot());
		expected.add(maker.n2);
		expected.add(maker.n4);
		expected.add(maker.n100);
		
		assertEquals(expected, dfs.search(maker.tree.getRoot(), maker.n100));
	}
	@Test @Ignore
	public void testBreadthFirstStrategy()
	{
		BreadthFirstSearchStrategy bfs = new BreadthFirstSearchStrategy();
		List<Tree<Integer>.Node> expected = new ArrayList<Tree<Integer>.Node>();
		expected.add(maker.tree.getRoot());
		expected.add(maker.n3);
		expected.add(maker.n100);
		assertEquals(expected, bfs.search(maker.tree.getRoot(), maker.n100));
	}
	
	@Test
	public void testNodeEquals()
	{
		Tree<String> tree = new Tree<String>("");
		Tree<String>.Node nodeEquiv1 = tree.new Node("the same");
		Tree<String>.Node nodeEquiv2 = tree.new Node("the same");
		assertEquals(nodeEquiv1, nodeEquiv2);
	}
}
