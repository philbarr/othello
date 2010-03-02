package com.ou.pbarr.nets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class Network<T>
{
	Strategy strategy;
	Agenda agenda;
	Node root;
	
	public Network(T state)
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
		return agenda.last();
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
		
		public void addChild(Node child)
		{
			children.add(child);
		}

		public T getState()
		{
			return state;
		}
	}
	
	public class Agenda
	{
		private List<Node> agenda = new ArrayList<Node>();

		public Agenda(Node root)
		{
			agenda.add(root);
		}

		public Node last()
		{
			// TODO Auto-generated method stub
			return null;
		}

		public T[] getAllStates()
		{
			T[] states = null;
			agenda.toArray(states);
			return null;
		}

	}
}
