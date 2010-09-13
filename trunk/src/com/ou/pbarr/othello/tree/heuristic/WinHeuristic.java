package com.ou.pbarr.othello.tree.heuristic;

import com.ou.pbarr.othello.model.OthelloStateExpandable;
import com.ou.pbarr.othello.model.Token.Type;
import com.ou.pbarr.othello.tree.Tree;

public class WinHeuristic implements Heuristic<OthelloStateExpandable>
{
	@Override
	public int test(Tree<OthelloStateExpandable>.Node node)
	{
		OthelloStateExpandable state = node.getState();
		boolean noMovesForBlack = state.getPossibleNextPositions(Type.BLACK).isEmpty();
		boolean noMovesForWhite = state.getPossibleNextPositions(Type.WHITE).isEmpty();
		Type otherColour = state.getTypeToExpandFor() == Type.BLACK ? Type.WHITE	: Type.BLACK;
		boolean currentColourWins = state.getTokenCountFor(state.getTypeToExpandFor()) > state.getTokenCountFor(otherColour);

		if (noMovesForBlack && noMovesForWhite && currentColourWins)
		{
			return 1;
		}
		return 0;
	}

}
