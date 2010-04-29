package com.ou.pbarr.othello.tree;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import com.ou.pbarr.othello.tree.DepthFirstSearchStrategy;
import com.ou.pbarr.othello.tree.Tree;

public class DepthFirstSearchStrategyTest
{
	private Tree<String> tree;
	private Tree<String>.Node one, two, three, threeA, threeB, four;
	
	// Creates a tree like this:
	//               root
	//               one
	//               two
	//              three
	//         threeA  threeB
	//       four
	
	@Before
	public void setUp()
	{
		tree = new Tree<String>("root");
		one = tree.new Node("one");    
		two = tree.new Node("two");    
		three = tree.new Node("three");
		threeA = tree.new Node("threeA");
		threeB = tree.new Node("threeB");
		four = tree.new Node("four");		
		Tree<String>.Node root = tree.getRoot();
		
		
		three.addChild(threeA).addChild(four);
		three.addChild(threeB);
		root.addChild(one).addChild(two).addChild(three);
		
	}
	
	@Test
	public void testBacktracksToFindFindUntestedChildren()
	{
		DepthFirstSearchStrategyMock dfsMock = new DepthFirstSearchStrategyMock();
		
		List<Tree<String>.Node> expected = new ArrayList<Tree<String>.Node>();
		expected.add(tree.getRoot());
		expected.add(one);
		expected.add(two);
		expected.add(three);
		expected.add(threeA);
		expected.add(four);
		expected.add(threeB);
		dfsMock.search(tree.getRoot(), threeB);
		assertEquals(expected, dfsMock.verifyRecordedNodes());
	}
	
	@Test
	public void testNodeDoesNotExist()
	{
		DepthFirstSearchStrategy dfs = new DepthFirstSearchStrategy();
		assertEquals(new ArrayList<Tree<String>.Node>(), dfs.search(tree.getRoot(), tree.new Node("notInTree")));
	}
	
	@Test
	public void testFirstNodeIsGoal()
	{
		DepthFirstSearchStrategy bfs = new DepthFirstSearchStrategy();
		Tree<String> tree = new Tree<String>("goal");
		List<Tree<String>.Node> expected = new ArrayList<Tree<String>.Node>();
		expected.add(tree.getRoot());
		assertEquals(expected, bfs.search(tree.getRoot(), tree.getRoot()));
	}
}
