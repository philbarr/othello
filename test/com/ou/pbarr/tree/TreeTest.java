package com.ou.pbarr.tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.ou.pbarr.tree.DepthFirstStrategy;
import com.ou.pbarr.tree.Tree;

public class TreeTest
{
	Tree<Integer> network;
	
	// Sets up a network that looks like this:
	//                1
	//       2                 3
	//   4       5         6        7
	// 8   9  10   11   12   13  14   15
	@Before
	public void setUp()
	{
		network = new Tree<Integer>(1);
		Tree<Integer>.Node n15 = network.new Node(15);
		Tree<Integer>.Node n14 = network.new Node(14);
		Tree<Integer>.Node n13 = network.new Node(13);
		Tree<Integer>.Node n12 = network.new Node(12);
		Tree<Integer>.Node n11 = network.new Node(11);
		Tree<Integer>.Node n10 = network.new Node(10);
		Tree<Integer>.Node n9 = network.new Node(9);
		Tree<Integer>.Node n8 = network.new Node(8);
		Tree<Integer>.Node n7 = network.new Node(7);
		Tree<Integer>.Node n6 = network.new Node(6);
		Tree<Integer>.Node n5 = network.new Node(5);
		Tree<Integer>.Node n4 = network.new Node(4);
		Tree<Integer>.Node n3 = network.new Node(3);
		Tree<Integer>.Node n2 = network.new Node(2);
		Tree<Integer>.Node n1 = network.new Node(1);
		
		n1.addChild(n2);
		n1.addChild(n3);
		
		n2.addChild(n4);
		n2.addChild(n5);
		
		n3.addChild(n6);
		n3.addChild(n7);
		
		n4.addChild(n8);
		n4.addChild(n9);
		
		n5.addChild(n10);
		n5.addChild(n11);
		
		n6.addChild(n12);
		n6.addChild(n13);
		
		n7.addChild(n14);
		n7.addChild(n15);
	}
	@Test
	public void testDepthFirstStrategy()
	{
		Integer[] expected = new Integer[]{1,2,4,8,9,5,10,11,3,6,12,13,7,14,15};
		Integer[] actual = new Integer[15];
		network.setStrategy(new DepthFirstStrategy());
		for(int i = 0; i < 15; i++)
		{
			actual[i++] = network.nextSearchNode().getState();
		}
		
		assertArrayEquals(expected, actual);
	}
}
