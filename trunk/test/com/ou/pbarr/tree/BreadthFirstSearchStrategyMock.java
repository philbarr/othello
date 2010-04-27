package com.ou.pbarr.tree;

import java.util.ArrayList;
import java.util.List;

public class BreadthFirstSearchStrategyMock extends BreadthFirstSearchStrategy
{
	private List<Tree<?>.Node> recordedNodes = new ArrayList<Tree<?>.Node>();
	
	protected Tree<?>.Node find(List<Tree<?>.Node> node, Tree<?>.Node goal) 
	{
		recordedNodes.add(node.get(0));
		return super.find(node, goal);
	}

	public List<Tree<?>.Node> verifyRecordedNodes()
	{
		return recordedNodes;
	}
}
