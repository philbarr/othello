package com.ou.pbarr.nets;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class DepthFirstStrategyTest
{
	private Network<String> net;
	private Network<String>.Node root;
	
	// Creates a net like this:
	//               root
	//               one
	//               two
	//               three
	//          threeA   threeB
	@Before
	public void setUp()
	{
		net = new Network<String>("root");
		root = net.new Node("root");
		Network<String>.Node one = net.new Node("one");
		Network<String>.Node two = net.new Node("two");
		Network<String>.Node three = net.new Node("three");
		Network<String>.Node threeA = net.new Node("threeA");
		Network<String>.Node threeB = net.new Node("threeB");
		
		three.addChild(threeA);
		three.addChild(threeB);
		two.addChild(three);
		one.addChild(two);
		root.addChild(one);
	}

	@Test
	public void testManipulateAgenda()
	{
		Network<String>.Agenda agenda = net.new Agenda(root);
		
		DepthFirstStrategy dfs = new DepthFirstStrategy();
		dfs.manipulateAgenda(agenda);
		assertEquals(new String[]{"root","one"}, agenda.getAllStates());
	}
	
	
}
