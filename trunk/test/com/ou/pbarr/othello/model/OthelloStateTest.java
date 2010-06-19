package com.ou.pbarr.othello.model;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.ou.pbarr.othello.model.Token.Type;

public class OthelloStateTest
{
	private OthelloState state;

	@Before
	public void setUp()
	{
		state = new OthelloState();
	}
	
	@Test(expected=OutOfOthelloBoardBoundsException.class)
	public void testOutOfBoundsException() throws OutOfOthelloBoardBoundsException, TokenAlreadyExistsInSquareException
	{
		state.addToken(new Token(Token.Type.BLACK, 1,1)); // should pass
		state.addToken(new Token(Token.Type.WHITE, 8,8)); // should pass
		state.addToken(new Token(Token.Type.BLACK, 0,0)); // should fail
	}
	
	/*
	 * Creates a black token surrounded by whites and tests that getPossibleNextPositions()
	 * finds all 8 possible positions
	 */
	@Test
	public void testGetPossibleNextPositions1() throws OutOfOthelloBoardBoundsException, TokenAlreadyExistsInSquareException
	{
		state.addToken(new Token(Type.BLACK, 5, 5));
		state.addToken(new Token(Type.WHITE, 4, 4));
		state.addToken(new Token(Type.WHITE, 4, 5));
		state.addToken(new Token(Type.WHITE, 4, 6));
		state.addToken(new Token(Type.WHITE, 5, 4));
		state.addToken(new Token(Type.WHITE, 5, 6));
		state.addToken(new Token(Type.WHITE, 6, 4));
		state.addToken(new Token(Type.WHITE, 6, 5));
		state.addToken(new Token(Type.WHITE, 6, 6));
		List<Token> actual = state.getPossibleNextPositions(Token.Type.BLACK);
		assertEquals(8, actual.size());
		assertTrue(actual.contains(new Token(Type.BLACK, 3, 3)));
		assertTrue(actual.contains(new Token(Type.BLACK, 3, 5)));
		assertTrue(actual.contains(new Token(Type.BLACK, 3, 7)));
		assertTrue(actual.contains(new Token(Type.BLACK, 5, 3)));
		assertTrue(actual.contains(new Token(Type.BLACK, 5, 7)));
		assertTrue(actual.contains(new Token(Type.BLACK, 7, 3)));
		assertTrue(actual.contains(new Token(Type.BLACK, 7, 5)));
		assertTrue(actual.contains(new Token(Type.BLACK, 7, 7)));
	}
	
	@Test
	public void testGetPossibleNextPositions2() throws OutOfOthelloBoardBoundsException, TokenAlreadyExistsInSquareException
	{
		state.addToken(new Token(Type.BLACK, 5, 5));
		state.addToken(new Token(Type.WHITE, 4, 5));
		state.addToken(new Token(Type.WHITE, 4, 6));
		state.addToken(new Token(Type.WHITE, 5, 4));
		state.addToken(new Token(Type.WHITE, 5, 6));
		state.addToken(new Token(Type.WHITE, 6, 4));
		state.addToken(new Token(Type.WHITE, 6, 6));
		List<Token> actual = state.getPossibleNextPositions(Token.Type.BLACK);
		assertEquals(6, actual.size());
		assertTrue(actual.contains(new Token(Type.BLACK, 3, 5)));
		assertTrue(actual.contains(new Token(Type.BLACK, 3, 7)));
		assertTrue(actual.contains(new Token(Type.BLACK, 5, 3)));
		assertTrue(actual.contains(new Token(Type.BLACK, 5, 7)));
		assertTrue(actual.contains(new Token(Type.BLACK, 7, 3)));
		assertTrue(actual.contains(new Token(Type.BLACK, 7, 7)));
	}
	
	@Test
	public void testPlayToken() throws OutOfOthelloBoardBoundsException, TokenAlreadyExistsInSquareException, IllegalMoveException
	{
		Token[] expected = new Token[]{
				new Token(Type.BLACK, 4, 4),
				new Token(Type.BLACK, 4, 5),
				new Token(Type.BLACK, 4, 6)
		};
		state.addToken(new Token(Type.BLACK, 4, 4));
		state.addToken(new Token(Type.WHITE, 4, 5));
		state.playToken(new Token(Type.BLACK, 4, 6));
		Token[] actual = state.getTokens();
		assertArrayEquals(expected, actual);
	}
	
	@Test(expected=IllegalMoveException.class)
	public void testPlayTokenIllegalMove() throws TokenAlreadyExistsInSquareException, OutOfOthelloBoardBoundsException, IllegalMoveException
	{
		state.addToken(new Token(Type.BLACK, 1, 1));
		state.playToken(new Token(Type.BLACK, 6, 5));
	}
	
	@Test(expected = TokenAlreadyExistsInSquareException.class)
	public void testPlayTokenAlreadyExists() throws OutOfOthelloBoardBoundsException, TokenAlreadyExistsInSquareException, IllegalMoveException
	{
		state.addToken(new Token(Type.BLACK, 6, 6));
		state.playToken(new Token(Type.WHITE, 6, 6));
	}
	
	@Test(expected = TokenAlreadyExistsInSquareException.class)
	public void testAddTokenAlreadyExists() throws OutOfOthelloBoardBoundsException, TokenAlreadyExistsInSquareException
	{
		state.addToken(new Token(Type.BLACK, 6, 6));
		state.addToken(new Token(Type.WHITE, 6, 6));
	}
	
	@Test
	public void testPlayToken1() throws OutOfOthelloBoardBoundsException, TokenAlreadyExistsInSquareException, IllegalMoveException
	{
		Token[] expected = new Token[]{
				new Token(Type.WHITE, 4, 4),
				new Token(Type.BLACK, 4, 5),
				new Token(Type.BLACK, 5, 4),
				new Token(Type.WHITE, 5, 5),
				new Token(Type.BLACK, 5, 6),
				new Token(Type.WHITE, 6, 6),
		};
		state.addToken(new Token(Type.WHITE, 4, 4));
		state.addToken(new Token(Type.BLACK, 4, 5));
		state.addToken(new Token(Type.BLACK, 5, 4));
		state.addToken(new Token(Type.WHITE, 5, 5));
		state.playToken(new Token(Type.BLACK, 5, 6));
		state.playToken(new Token(Type.WHITE, 6, 6));
		Token[] actual = state.getTokens();
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void testGetPossibleNextPositionsAlternatingSequenceInline() throws TokenAlreadyExistsInSquareException, OutOfOthelloBoardBoundsException
	{
		Token[] expected = new Token[]{
				new Token(Type.WHITE, 2, 1)
		};
		state.addToken(new Token(Type.BLACK, 2,2));
		state.addToken(new Token(Type.WHITE, 2,3));
		state.addToken(new Token(Type.BLACK, 2,4));
		state.addToken(new Token(Type.WHITE, 2,5));
		Token[] actual = state.getPossibleNextPositions(Type.WHITE).toArray(new Token[1]);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void testGetPossibleNextPositions() throws TokenAlreadyExistsInSquareException, OutOfOthelloBoardBoundsException
	{
		state.addToken(new Token(Type.BLACK, 1, 1));
		Token[] actual = state.getPossibleNextPositions(Type.BLACK).toArray(new Token[0]);
		assertEquals(0, actual.length);
	}
	
	@Test
	public void testPlayTokenOnSide() throws TokenAlreadyExistsInSquareException, OutOfOthelloBoardBoundsException, IllegalMoveException
	{
		Token[] expected = new Token[]{
				new Token(Type.BLACK, 1, 6),
				new Token(Type.BLACK, 1, 7),
				new Token(Type.BLACK, 1, 8)
		};
		state.addToken(new Token(Type.BLACK, 1, 6));
		state.addToken(new Token(Type.WHITE, 1, 7));
		state.playToken(new Token(Type.BLACK, 1, 8));
		assertArrayEquals(expected, state.getTokens());
	}
	
	@Test(expected = IllegalMoveException.class)
	public void testPlayBlankSquares() throws TokenAlreadyExistsInSquareException, OutOfOthelloBoardBoundsException, IllegalMoveException
	{
		state.addToken(new Token(Type.BLACK, 1, 3));
		state.addToken(new Token(Type.WHITE, 1, 4));
		state.playToken(new Token(Type.BLACK, 1, 6));
	}
	
	@Test
	public void testGetPossibleNextPositions3() throws TokenAlreadyExistsInSquareException, OutOfOthelloBoardBoundsException
	{
		state.addToken(new Token(Type.BLACK, 1, 1));
		state.addToken(new Token(Type.BLACK, 1, 2));
		state.addToken(new Token(Type.WHITE, 1, 3));
		List<Token> tokens = state.getPossibleNextPositions(Type.WHITE);
		assertEquals(0, tokens.size());
	}
	
}
