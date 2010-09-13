package com.ou.pbarr.othello.tree.heuristic;

import com.ou.pbarr.othello.model.OthelloStateExpandable;
import com.ou.pbarr.othello.model.Token.Type;
import com.ou.pbarr.othello.tree.Tree;

public class SumValueHeuristic implements Heuristic<OthelloStateExpandable>
{
	@Override
	public int test(Tree<OthelloStateExpandable>.Node node)
	{
		OthelloStateExpandable state = node.getState();
		Type otherColour = state.getTypeToExpandFor() == Type.BLACK ? Type.WHITE	: Type.BLACK;
		return state.getTokenCountFor(state.getTypeToExpandFor()) - state.getTokenCountFor(otherColour);
	}
}
