package com.ou.pbarr.othello.tree;


public interface Heuristic<T>
{

	public boolean test(Tree<T>.Node node);

}
