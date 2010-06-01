package com.ou.pbarr.othello.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a game of Othello, with operations to add tokens, play tokens, and find next possible positions.
 * @author pbarr
 */
public class OthelloState
{
	public static int OTHELLO_BOARD_SIZE = 8;
	
	private Token[][] board = new Token[OTHELLO_BOARD_SIZE][OTHELLO_BOARD_SIZE];

	/**
	 * Adds a new token to the board. If a token already exists at this location, that token
	 * is overwritten with this new token.
	 * @param token the Token to add
	 */
	public void addToken(Token token)
	{
		board[token.getX() - 1][token.getY() - 1] = token;
	}

	/**
	 * Finds all possible new positions from the current board state, following the rules of the Othello game.
	 * @param colour the colour to find all next possible positions for
	 * @return all possible positions
	 */
	public List<Token> getPossibleNextPositions(Token.Type colour)
	{
		List<Token> nextPositions = new ArrayList<Token>();
		for (Token[] horizontalTokens : board) 
		{
			for (Token token : horizontalTokens)
			{
				if (token != null && token.getType() == colour)
				{
					nextPositions.addAll(getNextPossiblePositionsFor(token));
				}
			}
		}
		return nextPositions;
	}

	private List<Token> getNextPossiblePositionsFor(Token token)
	{
		List<Token> nextPositions = new ArrayList<Token>();
		for(int x = -1; x <= 1; x++)
		{
			for (int y = -1; y <= 1; y++)
			{
				// the loop gives us all vectors in all directions but we don't want (0, 0)
				if (x != 0 || y != 0)
				{
    				Token position = findPossiblePositionOnVector(token, x, y);
    				if (position != null)
    				{
    					nextPositions.add(position);
    				}
				}
			}
		}
		return nextPositions;
	}

	private Token findPossiblePositionOnVector(Token token, int xVector, int yVector)
	{
		boolean opponentColourFound = false;
		int currentX = token.getX();
		int currentY = token.getY();
		Token.Type playerColour = token.getType();
		Token.Type opponentColour = playerColour == Token.Type.BLACK ? Token.Type.WHITE : Token.Type.BLACK;
		
		while (currentX > 1 && currentX < OTHELLO_BOARD_SIZE &&
					currentY > 1 && currentY < OTHELLO_BOARD_SIZE)
		{
			// move along the path one step
			currentX += xVector;
			currentY += yVector;
			
			// check if the board has a token at this board position
			Token tokenToCheck = board[currentX-1][currentY-1];
			if (tokenToCheck != null)
			{
				if (tokenToCheck.getType() == opponentColour)
				{
					opponentColourFound = true;
				}
				else // it's our colour here, so can't be a position on this line
				{
					return null;
				}
			}
			else // if not, we have either found a board position, or just an empty square next to ours
			{
				// if opponent's colour was previously found, then this is a new position!
				if (opponentColourFound)
				{
					try
					{
						return new Token(playerColour, currentX, currentY);
					} 
					catch (OutOfOthelloBoardBoundsException e)
					{
						// shouldn't happen because of our previous checks
						e.printStackTrace();
						return null;
					}
				}
				else // just an empty square next to ours
				{
					return null;
				}
			}
		}
		return null;
	}
}
