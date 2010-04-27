package com.ou.pbarr.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import com.ou.pbarr.tree.Tree.Node;

public class BreadthFirstSearchStrategy implements SearchStrategy
{
	private Queue<Tree<?>.Node> agenda = new LinkedList<Tree<?>.Node>();

	public List<Tree<?>.Node> search(Tree<?>.Node start, Tree<?>.Node goal)
	{
		List<Tree<?>.Node> parents = new ArrayList<Tree<?>.Node>();
		parents.add(start);
		find(parents, goal);
		return (LinkedList<Tree<?>.Node>) agenda;
	}
	
	Tree<?>.Node find(List<Tree<?>.Node> parents, Tree<?>.Node goal)
	{
		List<Tree<?>.Node> newParents = new ArrayList<Tree<?>.Node>();
		
		for (Tree<?>.Node parent : parents)
		{
			for (Tree<?>.Node child : parent.getChildren())
			{
				if (child.equals(goal))
				{
					agenda.offer(child);
					return parent;
				}
				else
				{
					newParents.add(child);
				}
			}
		}
		
		Tree<?>.Node found = find(newParents, goal);
		
		if ( found != null)
		{
			agenda.offer(found);
			return found;
		}
		
		return null;
	}
	
	
}
