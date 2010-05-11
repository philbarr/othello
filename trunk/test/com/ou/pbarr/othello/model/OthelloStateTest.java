package com.ou.pbarr.othello.model;

import org.junit.Test;

public class OthelloStateTest
{
	@Test(expected=OutOfOthelloBoardBoundsException.class)
	public void testOutOfBoundsException() throws OutOfOthelloBoardBoundsException
	{
		OthelloState state = new OthelloState();
		state.addToken(OthelloState.TokenTypes.BLACK, 1,1); // should pass
		state.addToken(OthelloState.TokenTypes.WHITE, 8,8); // should pass
		state.addToken(OthelloState.TokenTypes.BLACK, 0,0); // should fail
	}
}
