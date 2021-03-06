package com.ou.pbarr.othello.tree;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ou.pbarr.othello.tree.strategy.DepthFirstSearchStrategyTest;
import com.ou.pbarr.othello.tree.strategy.RandomSelectionSearchStrategyTest;

@RunWith(Suite.class)
@SuiteClasses({
	/* BreadthFirstSearchStrategyTest.class, */
	DepthFirstSearchStrategyTest.class, 
	TreeTest.class,
	RandomSelectionSearchStrategyTest.class,
	})
public class AllTests
{}
