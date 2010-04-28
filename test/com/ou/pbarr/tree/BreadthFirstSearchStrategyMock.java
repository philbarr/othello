package com.ou.pbarr.tree;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class BreadthFirstSearchStrategyMock extends BreadthFirstSearchStrategy
{
	private List<List<Tree<?>.Node>> expectedNodes = new ArrayList<List<Tree<?>.Node>>();
	private List<List<Tree<?>.Node>> actualNodes = new ArrayList<List<Tree<?>.Node>>();
	
	protected Tree<?>.Node find(List<Tree<?>.Node> node, Tree<?>.Node goal) 
	{
		actualNodes.add(node);
		return super.find(node, goal);
	}

	public void verifyRecordedNodes()
	{
		assertEquals(expectedNodes, actualNodes);
	}
	
	public void expect(List<Tree<?>.Node> parents)
	{
		expectedNodes.add(parents);
	}
	
	public void expect(Tree<?>.Node singleParent)
	{
		List<Tree<?>.Node> list = new ArrayList<Tree<?>.Node>();
		list.add(singleParent);
		expect(list);
	}
}
