package com.ou.pbarr.othello.controller;

import com.ou.pbarr.othello.gui.View;
import com.ou.pbarr.othello.model.Model;

public interface Controller
{

	void applicationClosing();

	void setView(View view);

	void setModel(Model model);

}
