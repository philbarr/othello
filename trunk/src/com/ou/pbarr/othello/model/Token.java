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
		if ((x < 1 || x > OthelloState.OTHELLO_BOARD_SIZE) ||
				(y < 1 || y > OthelloState.OTHELLO_BOARD_SIZE))
		{
			throw new OutOfOthelloBoardBoundsException("Cannot assign a board token to x: " + x + "y: " + y);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		Token other = (Token) obj;
		if (type == null) 
		{
			if (other.type != null)
				return false;
		} 
		else if (!type.equals(other.type))
		{
			return false;
		}
		if (x != other.x)
		{
			return false;
		}
		if (y != other.y)
		{
			return false;
		}
		return true;
	}
	
	@Override
	public String toString()
	{
		return type + ": " + x + ": " + y;
	}
}
