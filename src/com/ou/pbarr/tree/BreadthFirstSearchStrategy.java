package com.ou.pbarr.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import com.ou.pbarr.tree.Tree.Node;

public class BreadthFirstSearchStrategy implements SearchStrategy
{
	private List<Tree<?>.Node> agenda = new ArrayList<Tree<?>.Node>();

	public List<Tree<?>.Node> search(Tree<?>.Node start, Tree<?>.Node goal)
	{
		List<Tree<?>.Node> parents = new ArrayList<Tree<?>.Node>();
		parents.add(start);
		find(parents, goal);
		return agenda;
	}
	
	Tree<?>.Node find(List<Tree<?>.Node> parents, Tree<?>.Node goal)
	{
		System.out.println(parents);
		List<Tree<?>.Node> newParents = new ArrayList<Tree<?>.Node>();
		
		for (Tree<?>.Node parent : parents)
		{
			
			for (Tree<?>.Node child : parent.getChildren())
			{
				if (child.equals(goal))
				{
					agenda.add(goal);
					return parent;
				}
				newParents.add(child);
			}
		}
		
		Tree<?>.Node found = find(newParents, goal);
		if (found != null)
		{
			agenda.add(found);
		}
		
	
		return null;
	}
	
	
}
