package com.ou.pbarr.othello.model;

import static org.junit.Assert.*;
import org.junit.Test;
import com.ou.pbarr.othello.model.Token.Type;
import com.ou.pbarr.othello.tree.RandomSelectionSearchStrategy;


public class OthelloModelTest
{
	@Test(expected = SearchStrategyDoesNotExistException.class)
	public void testSetStrategyByName() throws SearchStrategyDoesNotExistException
	{
		Model model = new OthelloModel();
		model.setStrategyByName("this strategy name doesn't exist");
	}
	
	@Test
	public void testNewGame() throws TokenAlreadyExistsInSquareException, OutOfOthelloBoardBoundsException, IllegalMoveException, SearchStrategyDoesNotExistException
	{
		Token[] expected = new Token[]
				                    {
															new Token(Type.WHITE, 4, 4),
															new Token(Type.BLACK, 4, 5),
															new Token(Type.BLACK, 5, 4),
															new Token(Type.WHITE, 5, 5),
				                    };
		OthelloModel model = new OthelloModel();
		model.addStrategy(new RandomSelectionSearchStrategy<OthelloStateExpandable>());
		model.setStrategyByName(model.getStrategyNames()[0]);
		model.newGame(Type.WHITE);
		assertEquals(Type.BLACK, model.getCurrentPlayer());
		assertEquals(Type.WHITE, model.getPlayerColour());
		assertArrayEquals(expected, model.getTokens());
		
	}
}
