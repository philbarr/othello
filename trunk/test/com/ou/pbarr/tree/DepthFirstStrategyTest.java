package com.ou.pbarr.tree;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class DepthFirstStrategyTest
{
	private Tree<String> net;
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
		net = new Tree<String>("root");
		one = net.new Node("one");    
		two = net.new Node("two");    
		three = net.new Node("three");
		threeA = net.new Node("threeA");
		threeB = net.new Node("threeB");
		four = net.new Node("four");		
		Tree<String>.Node root = net.getRoot();
		
		
		three.addChild(threeA).addChild(four);
		three.addChild(threeB);
		root.addChild(one).addChild(two).addChild(three);
		
	}

	@Test
	public void testRetrievesNextChildNodeWhenAvailable()
	{
		Tree<String>.Agenda agenda = net.new Agenda(net.getRoot());
		agenda.add(one);
		DepthFirstStrategy dfs = new DepthFirstStrategy();
		dfs.manipulateAgenda(agenda);
		
		List<Tree<String>.Node> expected = new ArrayList<Tree<String>.Node>();
		expected.add(net.getRoot());
		expected.add(one);
		expected.add(two);
		assertEquals(expected, agenda.getAllNodes());
	}	
	
	@Test
	public void testBacktracksToFindFindUntestedChildren()
	{
		Tree<String>.Agenda agenda = net.new Agenda(net.getRoot());
		agenda.add(one).addChild(two).addChild(three).addChild(threeA).addChild(four);
		DepthFirstStrategy dfs = new DepthFirstStrategy();
		dfs.manipulateAgenda(agenda);
		
		List<Tree<String>.Node> expected = new ArrayList<Tree<String>.Node>();
		expected.add(net.getRoot());
		expected.add(one);
		expected.add(two);
		expected.add(three);
		expected.add(threeB);
		
		assertEquals(expected, agenda.getAllNodes());
	}
}
