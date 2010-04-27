package com.ou.pbarr.tree;

/** Sets up a network that looks like this:
 *                1
 *      2                 3
 *   4       5         6        100
 * 8   100  10   11   12   13  14   15
 *    14  15
 * where '100' represents the goal state
 */
public class TreeMaker
{
	public Tree<Integer> tree;
	public Tree<Integer>.Node n2,n3,n4,n5,n6,n8,n10,n11,n12,n13,n14,n15,n100;
	
	public TreeMaker()
	{
		tree = new Tree<Integer>(1);
		 n100 = tree.new Node(100); 
		 n15 = tree.new Node(15);   
		 n14 = tree.new Node(14);   
		 n13 = tree.new Node(13);   
		 n12 = tree.new Node(12);   
		 n11 = tree.new Node(11);   
		 n10 = tree.new Node(10);   
		 n8 = tree.new Node(8);     
		 n6 = tree.new Node(6);     
		 n5 = tree.new Node(5);     
		 n4 = tree.new Node(4);     
		 n3 = tree.new Node(3);     
		 n2 = tree.new Node(2);     
		 
		tree.getRoot().addChild(n2);
		tree.getRoot().addChild(n3);
		
		n2.addChild(n4);
		n2.addChild(n5);
		
		n3.addChild(n6);
		n3.addChild(n100);
		
		n4.addChild(n8);
		n4.addChild(n100);
		
		n5.addChild(n10);
		n5.addChild(n11);
		
		n6.addChild(n12);
		n6.addChild(n13);
		
		n100.addChild(n14);
		n100.addChild(n15);
	}
}
