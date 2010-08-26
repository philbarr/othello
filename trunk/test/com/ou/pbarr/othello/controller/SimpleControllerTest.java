package com.ou.pbarr.othello.controller;

import static org.junit.Assert.*;
import org.junit.Test;
import com.ou.pbarr.othello.model.OthelloModel;
import com.ou.pbarr.othello.model.Token;

public class SimpleControllerTest
{
	@Test
	public void testSelectSquare()
	{
		OthelloModel model = new OthelloModel(); 
		SimpleController sc = new SimpleController();
		sc.setModel(model);
		sc.selectSquare(3, 4);
		Token square = model.getCurrentlySelectedSquare();
		assertEquals(3, square.getX());
		assertEquals(4, square.getY());
		assertEquals(null, square.getType());
	}
}
