package com.ou.pbarr.othello.model;

import com.ou.pbarr.othello.tree.SearchStrategy;

public interface Model
{

	void setStrategyByName(String substring);

	String[] getStrategyNames();
	
	void addStrategy(SearchStrategy strategy);

	Token[] getTokens();

	Token[] getPossibleNextMoves();

	void newGame();

}
