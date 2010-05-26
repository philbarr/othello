package com.ou.pbarr.othello.model;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class OthelloStateTest
{
	private OthelloState state;

	@Before
	public void setUp()
	{
		state = new OthelloState();
	}
	
	@Test(expected=OutOfOthelloBoardBoundsException.class)
	public void testOutOfBoundsException() throws OutOfOthelloBoardBoundsException
	{
		state.addToken(new Token(Token.Type.BLACK, 1,1)); // should pass
		state.addToken(new Token(Token.Type.WHITE, 8,8)); // should pass
		state.addToken(new Token(Token.Type.BLACK, 0,0)); // should fail
	}
	
	@Test
	public void testGetPossibleNextPositions() throws OutOfOthelloBoardBoundsException
	{
		List<Token> expected = new ArrayList<Token>();
		expected.add(new Token(Token.Type.BLACK, 5, 5));
		expected.add(new Token(Token.Type.WHITE, 5, 6));
		List<Token> actual = state.getPossibleNextPositions();
		assertEquals(1, actual.size());
		assertEquals(new Token(Token.Type.BLACK, 5, 7), actual.get(0));
	}
}
