package com.ou.pbarr.othello.tree;

import java.util.ArrayList;
import java.util.List;

public class Tree<T>
{
	SearchStrategy strategy;
	Node root;

	public Tree(T state)
	{
		root = new Node(state);
	}

	public Node getRoot()
	{
		return root;
	}

	public void setStrategy(SearchStrategy strategy)
	{
		this.strategy = strategy;
	}

	public T findNextState()
	{
		// add a heuristic for GOAL
		return (T) strategy.search(root, null).get(1).getState();
	}

	/**
	 * Node class
	 * 
	 * @author phil
	 * 
	 */
	public class Node
	{
		private List<Node> children = new ArrayList<Node>();
		private T state;

		public Node(T state)
		{
			this.state = state;
		}

		/**
		 * returns the added child to allow chaining of calls
		 * 
		 * @param child
		 * @return
		 */
		public Node addChild(Node child)
		{
			children.add(child);
			return child;
		}

		/**
		 * returns the added child to allow chaining of calls
		 * 
		 * @param child
		 * @return
		 */
		public Node addChild(T state)
		{
			Node child = new Node(state);
			return addChild(child);
		}

		public T getState()
		{
			return state;
		}

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((state == null) ? 0 : state.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
			{
				return true;
			}
			if (obj == null)
			{
				return false;
			}
			if (getClass() != obj.getClass())
			{
				return false;
			}
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
			{
				return false;
			}
			if (state == null)
			{
				if (other.state != null)
				{
					return false;
				}
			}
			else if (!state.equals(other.state))
			{
				return false;
			}
			return true;
		}

		private Tree<T> getOuterType()
		{
			return Tree.this;
		}

		public boolean hasChildren()
		{
			return (children != null && children.size() > 0);
		}

		public Node firstChild()
		{
			if (this.hasChildren())
			{
				return children.iterator().next();
			}
			else
			{
				return null;
			}
		}

		public String toString()
		{
			return state.toString();
		}

		public int childrenCount()
		{
			return children.size();
		}

		public Node getChild(int index)
		{
			return children.get(index);
		}

		@SuppressWarnings("unchecked")
		public List<Node> getChildren()
		{
			if (!this.hasChildren() && state instanceof Expandable)
			{
				Object[] children = ((Expandable) state).expand();
				if (children != null && children.length != 0)
				{
					for (Object child : children)
					{
						this.addChild((T) child);
					}
				}
			}
			return children;
		}

	}
}
