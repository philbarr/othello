package com.ou.pbarr.othello.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import com.ou.pbarr.othello.model.Token.Type;

/**
 * Represents a game of Othello, with operations to add tokens, play tokens, and
 * find next possible positions.
 * 
 * @author pbarr
 */
public class OthelloState
{
	public final static int OTHELLO_BOARD_SIZE = 8;
	private final static Logger LOG = Logger.getLogger(OthelloState.class
			.getName());
	private Token[][] board = new Token[OTHELLO_BOARD_SIZE][OTHELLO_BOARD_SIZE];
	private Token lastCreatedToken = null;

	/**
	 * Adds a new token to the board. If a token already exists at this location,
	 * a TokenAlreadyExistsInSquareException is thrown
	 * 
	 * @param token
	 *          the Token to add
	 * @throws TokenAlreadyExistsInSquareException
	 *           if a token already exists on the board at the square the token is
	 *           referring to
	 */
	public void addToken(Token token) throws TokenAlreadyExistsInSquareException
	{
		checkTokenSquareIsEmpty(token);
		board[token.getX() - 1][token.getY() - 1] = token;
		lastCreatedToken = token;
	}

	/**
	 * Finds all possible new positions from the current board state, following
	 * the rules of the Othello game.
	 * 
	 * @param colour
	 *          the colour to find all next possible positions for
	 * @return all possible positions
	 */
	public List<Token> getPossibleNextPositions(Token.Type colour)
	{
		Set<Token> nextPositions = new HashSet<Token>();
		for (Token[] horizontalTokens : board)
		{
			for (Token token : horizontalTokens)
			{
				if (token != null && token.getType() == colour)
				{
					nextPositions.addAll(findTokensOfTypeFrom(token, null));
				}
			}
		}
		return new ArrayList<Token>(nextPositions);
	}

	private List<Token> findTokensOfTypeFrom(Token startToken, Type endTokenType)
	{
		List<Token> nextPositions = new ArrayList<Token>();
		for (int x = -1; x <= 1; x++)
		{
			for (int y = -1; y <= 1; y++)
			{
				// this loop gives us all vectors in all directions but we don't want
				// (0, 0)
				if (!(x == 0 && y == 0))
				{
					Token position = null;
					if (endTokenType == null)
					{
						position = findBlankSquaresOnVector(startToken, x, y);
					}
					else
					{
						position = findTokenOfTypeOnVector(startToken, endTokenType, x, y);
					}
					if (position != null)
					{
						nextPositions.add(position);
					}
				}
			}
		}
		return nextPositions;
	}

	private Token findTokenOfTypeOnVector(Token startToken, Type endTokenType, int xVector, int yVector)
	{
		boolean opponentTypeFound = false;
		boolean currentTypeFound = false;
		Token.Type opponentType = startToken.getType() == Token.Type.BLACK ? Token.Type.WHITE
				: Token.Type.BLACK;
		Token tokenInSquareToCheck = null;
		int currentX = startToken.getX(), currentY = startToken.getY();
		for ( ; isInBounds(currentX, currentY) ; currentX+=xVector, currentY+=yVector)
		{
			// ignore starting square
			if (startToken.getX() == currentX && startToken.getY() == currentY)
			{
				continue;
			}
			
		// check the token at this board position
			tokenInSquareToCheck = getSquare(currentX, currentY);
			
			// end of the line if the square is empty
			if (tokenInSquareToCheck == null)
			{
				return null;
			}
			
			Token.Type typeOfTokenInSquareToCheck = tokenInSquareToCheck == null ? null
					: tokenInSquareToCheck.getType();

			// update the checks to see if what types we have found
			currentTypeFound = currentTypeFound
					| (typeOfTokenInSquareToCheck == startToken.getType());
			opponentTypeFound = opponentTypeFound
					| (typeOfTokenInSquareToCheck == opponentType);
			
			if (typeOfTokenInSquareToCheck == startToken.getType())
			{
				break;
			}
			
		}
		if (currentTypeFound && opponentTypeFound)
		{
			try
			{
				return new Token(startToken.getType(), currentX, currentY);
			}
			catch (OutOfOthelloBoardBoundsException e)
			{
				LOG.severe("Unexpected exception creating token: " + e.getMessage());
			}
		}
		return null;
	}

	private Token findBlankSquaresOnVector(Token startToken, int xVector,
			int yVector)
	{
		boolean opponentTypeFound = false;
		boolean currentTypeFound = false;
		int currentX = startToken.getX();
		int currentY = startToken.getY();
		Token.Type opponentType = startToken.getType() == Token.Type.BLACK ? Token.Type.WHITE
				: Token.Type.BLACK;

		Token tokenInSquareToCheck = null;

		do
		{
			// move along the path one step
			currentX += xVector;
			currentY += yVector;
			
			if (!isInBounds(currentX, currentY))
			{
				return null;
			}

			// check the token at this board position
			tokenInSquareToCheck = getSquare(currentX, currentY);
			Token.Type typeOfTokenInSquareToCheck = tokenInSquareToCheck == null ? null
					: tokenInSquareToCheck.getType();

			// update the checks to see if what types we have found
			currentTypeFound = currentTypeFound
					| (typeOfTokenInSquareToCheck == startToken.getType());
			opponentTypeFound = opponentTypeFound
					| (typeOfTokenInSquareToCheck == opponentType);
		}
		while (tokenInSquareToCheck != null);

		if (!currentTypeFound && opponentTypeFound)
		{
			try
			{
				return new Token(startToken.getType(), currentX, currentY);
			}
			catch (OutOfOthelloBoardBoundsException e)
			{
				LOG.severe("Unexpected exception creating token: " + e.getMessage());
			}
		}

		return null;
	}

	private boolean isInBounds(int x, int y)
	{
		return (x >= 1 && x <= OTHELLO_BOARD_SIZE && y >= 1 && y <= OTHELLO_BOARD_SIZE);
	}

	public Token[] getTokens()
	{
		List<Token> allTokens = new ArrayList<Token>();
		for (Token[] horizontalTokens : board)
		{
			for (Token token : horizontalTokens)
			{
				if (token != null)
				{
					allTokens.add(token);
				}
			}
		}
		return allTokens.toArray(new Token[allTokens.size()]);
	}

	public void clearAllTokens()
	{
		for (int i = 0; i < OTHELLO_BOARD_SIZE; i++)
		{
			for (int j = 0; j < OTHELLO_BOARD_SIZE; j++)
			{
				board[i][j] = null;
			}
		}
		lastCreatedToken = null;
	}

	/**
	 * Places a token on the board
	 * 
	 * @param token
	 * @throws TokenAlreadyExistsInSquareException
	 * @throws OutOfOthelloBoardBoundsException
	 * @throws IllegalMoveException
	 */
	public void playToken(Token token)
			throws TokenAlreadyExistsInSquareException,
			OutOfOthelloBoardBoundsException, IllegalMoveException
	{
		checkTokenIsLegal(token);
		addToken(token);
		List<Token> targets = findTokensOfTypeFrom(token, token.getType());
		for (Token target : targets)
		{
			changeTokensToType(token, target, token.getType());
		}
	}

	private void changeTokensToType(Token start, Token end, Type type)
			throws OutOfOthelloBoardBoundsException
	{
		int xUnit = start.getX() - end.getX() == 0 ? 0
				: start.getX() - end.getX() < 0 ? 1 : -1;
		int yUnit = start.getY() - end.getY() == 0 ? 0
				: start.getY() - end.getY() < 0 ? 1 : -1;

		for (int x = start.getX(), y = start.getY(); (!(x == end.getX() && y == end
				.getY())); x += xUnit, y += yUnit)
		{
			board[x - 1][y - 1] = new Token(type, x, y);
		}
	}

	private void checkTokenIsLegal(Token token)
			throws TokenAlreadyExistsInSquareException, IllegalMoveException
	{
		checkTokenSquareIsEmpty(token);
		if (findTokensOfTypeFrom(token, token.getType()).isEmpty())
		{
			throw new IllegalMoveException("At square: " + token.getX() + ", y: "
					+ token.getY());
		}
	}

	private void checkTokenSquareIsEmpty(Token token)
			throws TokenAlreadyExistsInSquareException
	{
		if (getSquare(token.getX(), token.getY()) != null)
		{
			throw new TokenAlreadyExistsInSquareException("At square: "
					+ token.getX() + ", y: " + token.getY());
		}
	}

	/*
	 * Turns board references (1 -8) into array references (0-7) to index into the
	 * array so that we don't have to -1 when indexing into the array elsewhere
	 */
	private Token getSquare(int x, int y)
	{
		return board[x - 1][y - 1];
	}
}
