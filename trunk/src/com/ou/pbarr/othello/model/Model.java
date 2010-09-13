package com.ou.pbarr.othello.model;

import com.ou.pbarr.othello.model.Token.Type;
import com.ou.pbarr.othello.tree.strategy.SearchStrategy;

public interface Model
{
	String[] getStrategyNames();
	
	Token[] getTokens();

	Token[] getPossibleNextMoves();

	void makeMove(int xSquare, int ySquare) throws OutOfOthelloBoardBoundsException, TokenAlreadyExistsInSquareException, IllegalMoveException;

	void newGame(Type colour);

	Type getCurrentPlayer();

	Type getPlayerColour();

	void generateMove() throws TokenAlreadyExistsInSquareException, OutOfOthelloBoardBoundsException, IllegalMoveException;

	boolean gameIsFinished();

	int getTokenCountFor(Type white);

	void addStrategy(SearchStrategy<OthelloStateExpandable> strategy);

	int getBoardSquareCount();

	Token getCurrentlySelectedSquare();

	void setCurrentlySelectedSquare(int xSquare, int ySquare);

	void setStrategyByName(Type type, String strategyName) throws SearchStrategyDoesNotExistException;
	
	Type getWinner();
}
