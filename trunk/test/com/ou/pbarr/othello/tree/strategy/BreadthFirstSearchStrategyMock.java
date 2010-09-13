package com.ou.pbarr.othello.tree.strategy;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import com.ou.pbarr.othello.tree.Tree;
import com.ou.pbarr.othello.tree.Tree.Node;

public class BreadthFirstSearchStrategyMock<T> extends BreadthFirstSearchStrategy<T>
{
	private List<List<Tree<T>.Node>> expectedNodes = new ArrayList<List<Tree<T>.Node>>();
	private List<List<Tree<T>.Node>> actualNodes = new ArrayList<List<Tree<T>.Node>>();
	
	protected Tree<T>.Node find(List<Tree<T>.Node> node) 
	{
		actualNodes.add(node);
		return super.find(node);
	}

	public void verifyRecordedNodes()
	{
		assertEquals(expectedNodes, actualNodes);
	}
	
	public void expect(List<Tree<T>.Node> parents)
	{
		expectedNodes.add(parents);
	}
	
	public void expect(Tree<T>.Node singleParent)
	{
		List<Tree<T>.Node> list = new ArrayList<Tree<T>.Node>();
		list.add(singleParent);
		expect(list);
	}
}
