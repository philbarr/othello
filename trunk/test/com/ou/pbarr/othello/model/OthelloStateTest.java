package com.ou.pbarr.othello.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
	public void testGetPossibleNextPositions1() throws OutOfOthelloBoardBoundsException
	{
		OthelloState state = new OthelloState();
		state.addToken(new Token(Token.Type.BLACK, 5, 5));
		state.addToken(new Token(Token.Type.WHITE, 4, 4));
		state.addToken(new Token(Token.Type.WHITE, 4, 5));
		state.addToken(new Token(Token.Type.WHITE, 4, 6));
		state.addToken(new Token(Token.Type.WHITE, 5, 4));
		state.addToken(new Token(Token.Type.WHITE, 5, 6));
		state.addToken(new Token(Token.Type.WHITE, 6, 4));
		state.addToken(new Token(Token.Type.WHITE, 6, 5));
		state.addToken(new Token(Token.Type.WHITE, 6, 6));
		List<Token> actual = state.getPossibleNextPositions(Token.Type.BLACK);
		assertEquals(8, actual.size());
		assertTrue(actual.contains(new Token(Token.Type.BLACK, 3, 3)));
		assertTrue(actual.contains(new Token(Token.Type.BLACK, 3, 5)));
		assertTrue(actual.contains(new Token(Token.Type.BLACK, 3, 7)));
		assertTrue(actual.contains(new Token(Token.Type.BLACK, 5, 3)));
		assertTrue(actual.contains(new Token(Token.Type.BLACK, 5, 7)));
		assertTrue(actual.contains(new Token(Token.Type.BLACK, 7, 3)));
		assertTrue(actual.contains(new Token(Token.Type.BLACK, 7, 5)));
		assertTrue(actual.contains(new Token(Token.Type.BLACK, 7, 7)));
	}
	
	@Test
	public void testGetPossibleNextPositions2() throws OutOfOthelloBoardBoundsException
	{
		OthelloState state = new OthelloState();
		state.addToken(new Token(Token.Type.BLACK, 5, 5));
		state.addToken(new Token(Token.Type.WHITE, 4, 5));
		state.addToken(new Token(Token.Type.WHITE, 4, 6));
		state.addToken(new Token(Token.Type.WHITE, 5, 4));
		state.addToken(new Token(Token.Type.WHITE, 5, 6));
		state.addToken(new Token(Token.Type.WHITE, 6, 4));
		state.addToken(new Token(Token.Type.WHITE, 6, 6));
		List<Token> actual = state.getPossibleNextPositions(Token.Type.BLACK);
		assertEquals(6, actual.size());
		assertTrue(actual.contains(new Token(Token.Type.BLACK, 3, 5)));
		assertTrue(actual.contains(new Token(Token.Type.BLACK, 3, 7)));
		assertTrue(actual.contains(new Token(Token.Type.BLACK, 5, 3)));
		assertTrue(actual.contains(new Token(Token.Type.BLACK, 5, 7)));
		assertTrue(actual.contains(new Token(Token.Type.BLACK, 7, 3)));
		assertTrue(actual.contains(new Token(Token.Type.BLACK, 7, 7)));
	}
}
