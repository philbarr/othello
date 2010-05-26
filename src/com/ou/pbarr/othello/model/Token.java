package com.ou.pbarr.othello.model;

public class Token
{
	public enum Type
	{
		BLACK,
		WHITE
	}

	private Type type;
	private int x;
	private int y;
	
	public Token(Type type, int x, int y) throws OutOfOthelloBoardBoundsException
	{
		if ((x < 1 || x > 8) ||
				(y < 1 || y > 8))
		{
			throw new OutOfOthelloBoardBoundsException("Cannot assign a board token to x: " + "y: " + y);
		}
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public Type getType()
	{
		return type;
	}

	public void setType(Type type)
	{
		this.type = type;
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

}
