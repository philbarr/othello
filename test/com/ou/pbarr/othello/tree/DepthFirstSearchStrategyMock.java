package com.ou.pbarr.othello.tree;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSearchStrategyMock<T> extends DepthFirstSearchStrategy<T>
{
	private List<Tree<T>.Node> recordedNodes = new ArrayList<Tree<T>.Node>();
	
	protected Tree<T>.Node find(Tree<T>.Node node) 
	{
		recordedNodes.add(node);
		return super.find(node);
	}

	public List<Tree<T>.Node> verifyRecordedNodes()
	{
		return recordedNodes;
	}
}
