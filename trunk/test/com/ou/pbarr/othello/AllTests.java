package com.ou.pbarr.othello;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	com.ou.pbarr.othello.model.AllTests.class,
	com.ou.pbarr.othello.tree.AllTests.class,
	})
public class AllTests
{}
