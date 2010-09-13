package com.ou.pbarr.othello.tree;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import com.ou.pbarr.othello.model.OthelloStateExpandable;
import com.ou.pbarr.othello.model.OutOfOthelloBoardBoundsException;
import com.ou.pbarr.othello.model.Token;
import com.ou.pbarr.othello.model.TokenAlreadyExistsInSquareException;
import com.ou.pbarr.othello.model.Token.Type;
import com.ou.pbarr.othello.tree.heuristic.Heuristic;
import com.ou.pbarr.othello.tree.strategy.BreadthFirstSearchStrategy;
import com.ou.pbarr.othello.tree.strategy.DepthFirstSearchStrategy;
import com.ou.pbarr.othello.tree.strategy.RandomSelectionSearchStrategy;

public class TreeTest
{
	private TreeMaker maker;
	

	@Before
	public void setUp()
	{
		maker = new TreeMaker();
	}
	@Test
	public void testDepthFirstStrategy()
	{
		DepthFirstSearchStrategy<Integer> dfs = new DepthFirstSearchStrategy<Integer>();
		
		List<Tree<Integer>.Node> expected = new ArrayList<Tree<Integer>.Node>();
		expected.add(maker.tree.getRoot());
		expected.add(maker.n2);
		expected.add(maker.n4);
		expected.add(maker.n100);
		
		assertEquals(expected, dfs.search(maker.tree.getRoot(), maker.n100));
	}
	@Test @Ignore
	public void testBreadthFirstStrategy()
	{
		BreadthFirstSearchStrategy<Integer> bfs = new BreadthFirstSearchStrategy<Integer>();
		List<Tree<Integer>.Node> expected = new ArrayList<Tree<Integer>.Node>();
		expected.add(maker.tree.getRoot());
		expected.add(maker.n3);
		expected.add(maker.n100);
		assertEquals(expected, bfs.search(maker.tree.getRoot(), maker.n100));
	}
	
	@Test
	public void testNodeEquals()
	{
		Tree<String> tree = new Tree<String>("");
		Tree<String>.Node nodeEquiv1 = tree.new Node("the same");
		Tree<String>.Node nodeEquiv2 = tree.new Node("the same");
		assertEquals(nodeEquiv1, nodeEquiv2);
	}
	
	@Test
	public void testOthelloState() throws TokenAlreadyExistsInSquareException, OutOfOthelloBoardBoundsException
	{
		OthelloStateExpandable state = new OthelloStateExpandable(Type.BLACK);
		state.addToken(new Token(Type.BLACK, 4, 4));
		state.addToken(new Token(Type.WHITE, 4, 5));
		state.addToken(new Token(Type.WHITE, 5, 4));
		List<Token> expected = new ArrayList<Token>();
		expected.add(new Token(Type.BLACK, 4, 6));
		expected.add(new Token(Type.BLACK, 6, 4));
		Tree<OthelloStateExpandable> tree = new Tree<OthelloStateExpandable>(state);
		tree.setStrategy(new RandomSelectionSearchStrategy<OthelloStateExpandable>());
		OthelloStateExpandable newState = tree.findNextState();
		assertTrue(expected.contains(newState.getLastCreatedToken()));
	}
	
	/*
	 * The AI is playing as BLACK, and is faced with 
	 */
	@Test
	public void testHeuristic() throws TokenAlreadyExistsInSquareException, OutOfOthelloBoardBoundsException
	{
		OthelloStateExpandable state = new OthelloStateExpandable(Type.BLACK, 8);
		state.addToken(new Token(Type.WHITE, 2, 1));
		state.addToken(new Token(Type.BLACK, 3, 1));
		state.addToken(new Token(Type.WHITE, 4, 1));
		
		Tree<OthelloStateExpandable> tree = new Tree<OthelloStateExpandable>(state);
		tree.setStrategy(new DepthFirstSearchStrategy<OthelloStateExpandable>());
		tree.setHeuristic(new Heuristic<OthelloStateExpandable>(){

			@Override
			public int test(Tree<OthelloStateExpandable>.Node node)
			{
				OthelloStateExpandable state = node.getState();
				boolean noMovesForBlack = state.getPossibleNextPositions(Type.BLACK).isEmpty();
				boolean noMovesForWhite = state.getPossibleNextPositions(Type.WHITE).isEmpty();
				boolean blackWins = state.getTokenCountFor(Type.BLACK) > state.getTokenCountFor(Type.WHITE);
				if ( noMovesForBlack &&	noMovesForWhite && blackWins)
				{
					return 1;
				}
				return 0;
			}});
		
		OthelloStateExpandable newState = tree.findNextState();
		assertEquals(new Token(Type.BLACK, 1, 1), newState.getLastCreatedToken());
	}
}
