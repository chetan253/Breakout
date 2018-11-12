
package com.action;

import java.awt.*;
import javax.swing.*;
import com.UI.*;
import com.constants.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameGround extends JFrame implements Constants {

	private Time timer;
	public static Logger logger = LogManager.getLogger(GameGround.class);

	public GameGround() {

		super("Breakout Game");
		logger.debug(" Intializing game layout");
		setSize(frameWidth, frameHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFocusable(true);
		setLayout(new BorderLayout());
		timer = new Time();
		Breakout breakoutGame = new Breakout(this, timer);
		ButtonPanel buttonPanel = new ButtonPanel(this, breakoutGame, timer);
		this.add(buttonPanel, BorderLayout.LINE_END);
		this.setVisible(true);
	}

}