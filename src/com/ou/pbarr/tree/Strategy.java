package com.ou.pbarr.tree;


public interface Strategy
{
	/**
	 * 
	 * @param agenda
	 * @return true if the Agenda is exhausted
	 */
	boolean manipulateAgenda(Tree<?>.Agenda agenda);
}
