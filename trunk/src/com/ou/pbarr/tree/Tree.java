package com.ou.pbarr.tree;

import java.util.ArrayList;
import java.util.List;

public class Tree<T>
{
	Strategy strategy;
	Agenda agenda;
	Node root;

	public Tree(T state)
	{
		root = new Node(state);
		agenda = new Agenda(root);
	}

	public Node getRoot()
	{
		return root;
	}

	public Node nextSearchNode()
	{
		strategy.manipulateAgenda(agenda);
		return agenda.getLast();
	}

	public void setStrategy(Strategy strategy)
	{
		this.strategy = strategy;
	}

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
			} else if (!state.equals(other.state))
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

	}

	public class Agenda
	{
		private List<Node> agendaList = new ArrayList<Node>();
		

		public Agenda(Node root)
		{
			agendaList.add(root);
		}

		@SuppressWarnings("unchecked")
		public T[] getAllStates()
		{
			return (T[]) agendaList.toArray(new Object[]{});
		}

		public Node add(Node node)
		{
			agendaList.add(node);
			return node;
		}

		public Node getLast()
		{
			return agendaList.get(agendaList.size() - 1);
		}

		public List<Node> getAllNodes()
		{
			return agendaList;
		}

		public Node trimLast()
		{
			if (agendaList != null && agendaList.size() >= 2)
			{
				Node penultimate = agendaList.get(agendaList.size() - 2);
				agendaList.remove(agendaList.size() - 1);
				return penultimate;
			}
			if (agendaList.size() == 1)
			{
				agendaList.remove(0);
			}
			return null;
		}

		public int size()
		{
			return agendaList.size();
		}

		public Node get(int index)
		{
			return agendaList.get(index);
		}

	}
}
