package com.ou.pbarr.othello.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import com.ou.pbarr.othello.controller.Controller;
import com.ou.pbarr.othello.gui.GameToken.Colour;
import com.ou.pbarr.othello.model.Model;
import com.ou.pbarr.othello.model.Token;

public class OthelloMainFrame extends JFrame implements View, ActionListener,
		OthelloBoardPanelListener
{
	private final static Logger LOG = Logger.getLogger(OthelloMainFrame.class
			.getName());
	private Model model;
	private Controller controller;
	private OthelloBoardPanel board;
	private JLabel infoLabel;
	private boolean boardFrozen = false;

	public OthelloMainFrame(Controller controller, Model model)
	{
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.controller = controller;
		this.model = model;

		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				OthelloMainFrame.this.controller.quit();
			}
		});

		this.setJMenuBar(getFileMenu());
		this.setContentPane(getMainPanel());
		this.setSize(400, 400);
		this.setTitle("Othello");
		this.setVisible(true);
		LOG.info("OthelloMainFrame constructed");
	}

	private JPanel getMainPanel()
	{
		JPanel mainPane = new JPanel();
		mainPane.setLayout(new BorderLayout());
		mainPane.add(getBoard(), BorderLayout.CENTER);
		mainPane.add(getInfoLabel(), BorderLayout.NORTH);
		mainPane.add(getStrategyOptions(), BorderLayout.EAST);
		return mainPane;
	}

	private JLabel getInfoLabel()
	{
		infoLabel = new JLabel();
		infoLabel.setSize(100, 20);
		return infoLabel;
	}

	private JPanel getStrategyOptions()
	{
		ButtonGroup group = new ButtonGroup();
		JPanel buttonPanel = new JPanel(new GridLayout(10, 1));

		for (String strategy : controller.getStrategies())
		{
			JRadioButton button = new JRadioButton(strategy);
			button.addActionListener(this);
			buttonPanel.add(button);
			group.add(button);
		}
		if (group.getButtonCount() > 0)
		{
			group.getElements().nextElement().setSelected(true);
		}
		return buttonPanel;
	}

	private JPanel getBoard()
	{
		board = new OthelloBoardPanel(model.getBoardSquareCount());
		board.addOthelloBoardPanelListener(this);
		return board;
	}

	private JMenuBar getFileMenu()
	{
		JMenu fileMenu = new JMenu("File");
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem loadMenuItem = new JMenuItem("Load");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		newMenuItem.addActionListener(this);
		loadMenuItem.addActionListener(this);
		saveMenuItem.addActionListener(this);
		exitMenuItem.addActionListener(this);
		fileMenu.add(newMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(loadMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);

		return menuBar;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() instanceof JMenuItem)
		{
			String text = ((JMenuItem) e.getSource()).getText();
			controller.callActionByName(text);
		}
		else if (e.getSource() instanceof JRadioButton)
		{
			String text = ((JRadioButton) e.getSource()).getText();
			controller.changeStrategyByName(text);
		}
	}

	/**
	 * Retrieve the current board state from the model and update the user
	 * interface
	 */
	public void updateBoard()
	{
		List<GameToken> gameTokens = new ArrayList<GameToken>();

		for (Token token : model.getTokens())
		{
			Colour colour = null;
			if (token.getType() == Token.Type.BLACK)
			{
				colour = Colour.DARK;
			}
			else if (token.getType() == Token.Type.WHITE)
			{
				colour = Colour.LIGHT;
			}
			gameTokens.add(new GameToken(colour, token.getX(), token.getY()));
		}

		if (!boardFrozen)
		{
			for (Token token : model.getPossibleNextMoves())
			{
				gameTokens.add(new GameToken(Colour.GHOST, token.getX(), token.getY()));
			}
		}

		board.setTokens(gameTokens);
		repaint();
	}

	@Override
	public String askForHumanPlayerColour()
	{
		Object selectedValue = JOptionPane.showInputDialog(this,
				"Which colour would you like to play as?", "Select Colour",
				JOptionPane.INFORMATION_MESSAGE, null, new String[] {
						PLAYER_COLOUR_BLACK, PLAYER_COLOUR_WHITE }, null);
		return (String) selectedValue;
	}

	@Override
	public void squareClicked(int xSquare, int ySquare)
	{
		LOG.info("OthelloBoardPanel clicked square: x: " + xSquare + ", y: "
				+ ySquare);
		if (!boardFrozen)
		{
			controller.selectSquare(xSquare, ySquare);
		}
	}

	@Override
	public void displayError(String error)
	{
		JOptionPane.showMessageDialog(this, error, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void setInfoMessage(String message)
	{
		infoLabel.setText(message);
	}

	@Override
	public void setBoardFrozenState(boolean frozen)
	{
		boardFrozen = frozen;
	}
}