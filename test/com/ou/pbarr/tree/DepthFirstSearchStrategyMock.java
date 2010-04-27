package com.ou.pbarr.tree;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSearchStrategyMock extends DepthFirstSearchStrategy
{
	private List<Tree<?>.Node> recordedNodes = new ArrayList<Tree<?>.Node>();
	
	protected Tree<?>.Node find(Tree<?>.Node node, Tree<?>.Node goal) 
	{
		recordedNodes.add(node);
		return super.find(node, goal);
	}

	public List<Tree<?>.Node> verifyRecordedNodes()
	{
		return recordedNodes;
	}
}
