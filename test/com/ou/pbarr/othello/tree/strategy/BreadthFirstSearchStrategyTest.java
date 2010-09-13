package com.ou.pbarr.othello.tree.strategy;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import com.ou.pbarr.othello.tree.Tree;
import com.ou.pbarr.othello.tree.TreeMaker;
import com.ou.pbarr.othello.tree.Tree.Node;

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
		BreadthFirstSearchStrategyMock<String> bfsMock = new BreadthFirstSearchStrategyMock<String>();
		
		List<Tree<String>.Node> expected = new ArrayList<Tree<String>.Node>();
		expected.add(tree.getRoot());
		expected.add(one);
		expected.add(two);
		expected.add(three);
		expected.add(threeA);
		expected.add(threeB);
		expected.add(four);
		bfsMock.search(tree.getRoot(), four);
		bfsMock.verifyRecordedNodes();
	}
	
	@Test
	public void testNodeDoesNotExist()
	{
		BreadthFirstSearchStrategy<String> bfs = new BreadthFirstSearchStrategy<String>();
		assertEquals(new ArrayList<Tree<String>.Node>(), bfs.search(tree.getRoot(), tree.new Node("notInTree")));
	}
	
	@Test
	public void testBreadthFirstStrategy()
	{
		BreadthFirstSearchStrategyMock<Integer> bfsMock = new BreadthFirstSearchStrategyMock<Integer>();
		final TreeMaker maker = new TreeMaker();
		bfsMock.expect(maker.tree.getRoot());
		bfsMock.expect(new ArrayList<Tree<Integer>.Node>()
				{{add (maker.n2);}});
		bfsMock.expect(maker.n2);
		
		List<Tree<Integer>.Node> expected = new ArrayList<Tree<Integer>.Node>();
		expected.add(maker.tree.getRoot());
		expected.add(maker.n2);
		expected.add(maker.n3);
		expected.add(maker.n4);
		expected.add(maker.n5);
		expected.add(maker.n6);
		expected.add(maker.n100);
		bfsMock.search(maker.tree.getRoot(), maker.n100);
		bfsMock.verifyRecordedNodes();
	}
	
	@Test
	public void testFirstNodeIsGoal()
	{
		BreadthFirstSearchStrategy<String> bfs = new BreadthFirstSearchStrategy<String>();
		Tree<String> tree = new Tree<String>("goal");
		
		assertEquals(tree.getRoot(), bfs.search(tree.getRoot(), tree.getRoot()));
	}
}
