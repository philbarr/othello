package com.ou.pbarr.othello.tree.strategy;

import java.util.ArrayList;
import java.util.List;

import com.ou.pbarr.othello.tree.Tree;
import com.ou.pbarr.othello.tree.Tree.Node;

public class DepthFirstLimitedSearchStrategyMock<T> extends DepthFirstLimitedSearchStrategy<T>
{
	private List<Tree<T>.Node> recordedNodes = new ArrayList<Tree<T>.Node>();

	public DepthFirstLimitedSearchStrategyMock(int limit)
	{
		super(limit);
	}
	
	@Override
	protected Tree<T>.Node find(Tree<T>.Node node, int depth) 
	{
		recordedNodes.add(node);
		return super.find(node, depth);
	}

	public List<Tree<T>.Node> verifyRecordedNodes()
	{
		return recordedNodes;
	}

}
