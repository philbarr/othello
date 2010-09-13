package com.ou.pbarr.othello.tree.strategy;

import java.util.ArrayList;
import java.util.List;

import com.ou.pbarr.othello.tree.Tree;
import com.ou.pbarr.othello.tree.Tree.Node;

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
