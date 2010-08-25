package com.ou.pbarr.othello.tree;

import static org.junit.Assert.*;
import org.junit.Test;
import com.ou.pbarr.othello.model.OthelloStateExpandable;
import com.ou.pbarr.othello.model.Token.Type;

public class OthelloStateExpandableTest
{
	@Test
	public void testConstructorBoardSize()
	{
		OthelloStateExpandable state = new OthelloStateExpandable(Type.BLACK, 3);
		assertEquals(3, state.getBoardSize());
	}
}
