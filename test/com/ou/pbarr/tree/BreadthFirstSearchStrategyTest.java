package com.ou.pbarr.tree;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class BreadthFirstSearchStrategyTest
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
	public void testChecksForGoalPerDepth()
	{
		BreadthFirstSearchStrategyMock bfsMock = new BreadthFirstSearchStrategyMock();
		
		List<Tree<String>.Node> expected = new ArrayList<Tree<String>.Node>();
		expected.add(tree.getRoot());
		expected.add(one);
		expected.add(two);
		expected.add(three);
		expected.add(threeA);
		expected.add(threeB);
		expected.add(four);
		bfsMock.search(tree.getRoot(), threeB);
		assertEquals(expected, bfsMock.verifyRecordedNodes());
	}
	
	@Test
	public void testNodeDoesNotExist()
	{
		BreadthFirstSearchStrategy bfs = new BreadthFirstSearchStrategy();
		assertEquals(new ArrayList<Tree<String>.Node>(), bfs.search(tree.getRoot(), tree.new Node("notInTree")));
	}
	
	@Test
	public void testBreadthFirstStrategy()
	{
		BreadthFirstSearchStrategyMock bfsMock = new BreadthFirstSearchStrategyMock();
		TreeMaker maker = new TreeMaker();
		List<Tree<Integer>.Node> expected = new ArrayList<Tree<Integer>.Node>();
		expected.add(maker.tree.getRoot());
		expected.add(maker.n2);
		expected.add(maker.n3);
		expected.add(maker.n4);
		expected.add(maker.n5);
		expected.add(maker.n6);
		expected.add(maker.n100);
		bfsMock.search(maker.tree.getRoot(), maker.n100);
		assertEquals(expected, bfsMock.verifyRecordedNodes());
	}
	
	@Test
	public void testFirstNodeIsGoal()
	{
		BreadthFirstSearchStrategy bfs = new BreadthFirstSearchStrategy();
		Tree<String> tree = new Tree<String>("goal");
		
		assertEquals(tree.getRoot(), bfs.search(tree.getRoot(), tree.getRoot()));
	}
}
