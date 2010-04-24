package com.ou.pbarr.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class BreadthFirstStrategy implements Strategy
{

	public List<Tree<?>.Node> search(Tree<?>.Node start, Tree<?>.Node goal)
	{
		List<Tree<?>.Node> agenda = new ArrayList<Tree<?>.Node>();
		return agenda;
//		agenda = find(start, goal);
//		Collections.reverse(agenda);
//		return agenda;
	}
	
	private Stack<Tree<?>.Node> find(Tree<?>.Node node, Tree<?>.Node goal)
	{
		Stack<Tree<?>.Node> agenda = new Stack<Tree<?>.Node>();
		agenda.push(node);
		if (agenda.peek().equals(goal))
		{
			return agenda;
		}
		for (Tree<?>.Node child : agenda.peek().getChildren())
		{
			if (child.equals(goal))
			{
				agenda.push(child);
				return agenda;
			}
		}
		return null;
	}
	
	
}
