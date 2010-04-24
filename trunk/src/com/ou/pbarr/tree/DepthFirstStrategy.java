package com.ou.pbarr.tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DepthFirstStrategy implements Strategy
{
	private List<Tree<?>.Node> agenda = new LinkedList<Tree<?>.Node>();
	
	public List<Tree<?>.Node> search(Tree<?>.Node start, Tree<?>.Node goal)
	{
		find(start, goal);
		Collections.reverse((List<Tree<?>.Node>)agenda);
		return agenda;
	}
	
	private Tree<?>.Node find(Tree<?>.Node node, Tree<?>.Node goal)
	{
		if (node.equals(goal))
		{
			agenda.add(node);
			return node;
		}
		
		for (Tree<?>.Node child : node.getChildren())
		{
			Tree<?>.Node found = find(child, goal);
			if (found != null)
			{
				agenda.add(node);
				return node;
			}
		}
		
		return null;
	}
}
