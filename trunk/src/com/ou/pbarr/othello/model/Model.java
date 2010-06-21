package com.ou.pbarr.othello.model;

import com.ou.pbarr.othello.model.Token.Type;
import com.ou.pbarr.othello.tree.SearchStrategy;

public interface Model
{

	void setStrategyByName(String substring) throws SearchStrategyDoesNotExistException;

	String[] getStrategyNames();
	
	void addStrategy(SearchStrategy strategy);

	Token[] getTokens();

	Token[] getPossibleNextMoves();

	void makeMove(int xSquare, int ySquare) throws OutOfOthelloBoardBoundsException, TokenAlreadyExistsInSquareException, IllegalMoveException;

	void newGame(Type colour);

	Type getCurrentPlayer();

	Type getPlayerColour();

	void generateMove();
}
