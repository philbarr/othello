package com.ou.pbarr.othello.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.ou.pbarr.othello.model.OthelloState;

public class OthelloBoardPanel extends JPanel
{
	private List<OthelloBoardPanelListener> listeners = new ArrayList<OthelloBoardPanelListener>();
	
	private Color darkSquareColour = Color.GRAY;
	private Color lightSquareColour = Color.WHITE;
	private Color darkTokenColour = Color.BLACK;
	private Color lightTokenColour = Color.WHITE;
	private Color ghostTokenColour = Color.GRAY.brighter();
	private Color darkTokenBorderColour = Color.DARK_GRAY;
	private Color lightTokenBorderColour = Color.DARK_GRAY;
	private Color ghostTokenBorderColour = Color.DARK_GRAY.brighter();

	private int tokenMargin = 20; // percentage of overall square size as a margin.

	private List<GameToken> tokens = null;
	private boolean drawGhostTokens = false;

	private int boardSize = 0;

	private int squareSize;

	public OthelloBoardPanel()
	{
		this.addMouseListener(new OthelloBoardPanelMouseAdapter());
	}
	
	public void addOthelloBoardPanelListener(OthelloBoardPanelListener listener)
	{
		listeners.add(listener);
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		boardSize = this.getWidth() > this.getHeight() ? this.getHeight()
				: this.getWidth();
		squareSize = boardSize / OthelloState.OTHELLO_BOARD_SIZE;
		boardSize = squareSize * OthelloState.OTHELLO_BOARD_SIZE; // allow for the rounding error we just introduced.
		drawBoard(g);
		drawTokens(g);
	}

	private void drawTokens(Graphics g)
	{
		if (tokens != null && !tokens.isEmpty())
		{
			int margin = this.getTokenMargin() * squareSize / 100;

			for (GameToken token : tokens)
			{
				int xToken = (token.getXPosition() * squareSize) - squareSize;
				int yToken = (token.getYPosition() * squareSize) - squareSize;

				Color fillColour = null;
				Color borderColour = null;

				if (token.getGameColour().equals(GameToken.Colour.LIGHT))
				{
					fillColour = this.getLightTokenColour();
					borderColour = this.getLightTokenBorderColour();
				}
				else if (token.getGameColour().equals(GameToken.Colour.DARK))
				{
					fillColour = this.getDarkTokenColour();
					borderColour = this.getDarkTokenBorderColour();
				}
				else if (token.getGameColour() == GameToken.Colour.GHOST
						&& drawGhostTokens == false)
				{
					if (drawGhostTokens)
					{
						fillColour = this.getGhostTokenColour();
						borderColour = this.getGhostTokenBorderColour();
					}
					else
					{
						continue;
					}
				}
				g.setColor(fillColour);
				g.fillOval(xToken + margin, yToken + margin, squareSize - (margin * 2),
						squareSize - (margin * 2));
				g.setColor(borderColour);
				g.drawOval(xToken + margin, yToken + margin, squareSize - (margin * 2),
						squareSize - (margin * 2));
			}
		}
	}

	private void drawBoard(Graphics g)
	{
		g.setColor(this.getLightSquareColour());
		g.fillRect(0, 0, boardSize, boardSize);

		// fill in the squares, each odd row gets moved along by a squareSize
		// to create a chequered board pattern.
		g.setColor(this.getDarkSquareColour());
		for (int colSquares = 0; colSquares < OthelloState.OTHELLO_BOARD_SIZE; colSquares += 2)
		{
			for (int rowSquares = 0; rowSquares < OthelloState.OTHELLO_BOARD_SIZE; rowSquares++)
			{
				int xPos = rowSquares % 2 == 0 ? colSquares * squareSize
						: (colSquares * squareSize) + squareSize;
				int yPos = rowSquares * squareSize;
				g.fillRect(xPos, yPos, squareSize, squareSize);
			}
		}
	}

	public void setTokens(List<GameToken> tokens)
	{
		this.tokens = tokens;
	}

	public void setDarkTokenColour(Color darkTokenColour)
	{
		this.darkTokenColour = darkTokenColour;
	}

	public Color getDarkTokenColour()
	{
		return darkTokenColour;
	}

	public void setLightTokenColour(Color lightTokenColour)
	{
		this.lightTokenColour = lightTokenColour;
	}

	public Color getLightTokenColour()
	{
		return lightTokenColour;
	}

	public void setDarkTokenBorderColour(Color darkTokenBorderColour)
	{
		this.darkTokenBorderColour = darkTokenBorderColour;
	}

	public Color getDarkTokenBorderColour()
	{
		return darkTokenBorderColour;
	}

	public void setLightTokenBorderColour(Color lightTokenBorderColour)
	{
		this.lightTokenBorderColour = lightTokenBorderColour;
	}

	public Color getLightTokenBorderColour()
	{
		return lightTokenBorderColour;
	}

	public Color getLightSquareColour()
	{
		return lightSquareColour;
	}

	public void setLightSquareColour(Color lightSquareColour)
	{
		this.lightSquareColour = lightSquareColour;
	}

	public Color getDarkSquareColour()
	{
		return darkSquareColour;
	}

	public void setDarkSquareColour(Color darkSquareColour)
	{
		this.darkSquareColour = darkSquareColour;
	}

	public void setTokenMargin(int tokenMargin)
	{
		this.tokenMargin = tokenMargin;
	}

	public int getTokenMargin()
	{
		return tokenMargin;
	}

	public Color getGhostTokenColour()
	{
		return ghostTokenColour;
	}
	
	public void setGhostTokenColour(Color ghostTokenColour)
	{
		this.ghostTokenColour = ghostTokenColour;
	}
	
	public Color getGhostTokenBorderColour()
	{
		return ghostTokenBorderColour;
	}
	
	public void setGhostTokenBorderColour(Color ghostTokenBorderColour)
	{
		this.ghostTokenBorderColour = ghostTokenBorderColour;
	}

	// simple test main.
	public static void main(String[] args)
	{
		List<GameToken> tokens = new ArrayList<GameToken>();
		tokens.add(new GameToken(GameToken.Colour.LIGHT, 3, 3));
		tokens.add(new GameToken(GameToken.Colour.DARK, 3, 4));
		tokens.add(new GameToken(GameToken.Colour.DARK, 4, 3));
		tokens.add(new GameToken(GameToken.Colour.LIGHT, 4, 4));
		tokens.add(new GameToken(GameToken.Colour.LIGHT, 4, 5));
		OthelloBoardPanel othelloBoardPanel = new OthelloBoardPanel();
		othelloBoardPanel.setTokens(tokens);

		JFrame frame = new JFrame("Othello");
		frame.add(othelloBoardPanel);
		frame.setSize(new Dimension(450, 480));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * This class listens for mouse clicks, determines which Othello square
	 * the mouse was clicked in and informs any registered
	 * OthelloBoardPanelListeners via a call to
	 * OthelloBoardPanelListener.squareClicked(int x, int y).
	 * 
	 * This class also deals with the drawing of GHOST tokens. GHOST tokens
	 * are only drawn when the mouse is within the bounds of the OthelloBoardPanel
	 * itself.
	 * @author phil
	 */
	private class OthelloBoardPanelMouseAdapter extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent e)
		{
			for (OthelloBoardPanelListener listener : listeners)
			{
				listener.squareClicked((e.getX() / squareSize) + 1, (e.getY()/squareSize) + 1);
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e)
		{
			drawGhostTokens = true;
			repaint();
		}
		
		@Override
		public void mouseExited(MouseEvent e)
		{
			drawGhostTokens = false;
			repaint();
		}
	}
}
