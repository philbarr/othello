package com.ou.pbarr.othello.tree.heuristic;

import com.ou.pbarr.othello.tree.Tree;
import com.ou.pbarr.othello.tree.Tree.Node;


public interface Heuristic<T>
{

	public int test(Tree<T>.Node node);

}
