package com.ou.pbarr.othello.model;

import org.junit.Test;


public class OthelloModelTest
{
	@Test(expected = SearchStrategyDoesNotExistException.class)
	public void testSetStrategyByName() throws SearchStrategyDoesNotExistException
	{
		Model model = new OthelloModel();
		model.setStrategyByName("this strategy name doesn't exist");
	}
}
