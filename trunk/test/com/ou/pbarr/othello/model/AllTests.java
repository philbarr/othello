package com.ou.pbarr.othello.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	OthelloStateTest.class,
	OthelloModelTest.class,
	})
public class AllTests
{}
