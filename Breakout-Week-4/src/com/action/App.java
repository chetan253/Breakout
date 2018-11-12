package com.action;

import javax.swing.SwingUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

	static GameGround ground;
	
	public static Logger logger = LogManager.getLogger(App.class); 

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				logger.info(" Application started");
				ground = new GameGround();
			}
		});
//		ground = new GameGround();
	}
}
