package com.action;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.UI.SquareStrategy;
import com.UI.Strategy;
import com.UI.WedgeStrategy;
import com.commands.MacroCommand;
import com.constants.Constants;
import com.controller.GameController;
import com.entity.Ball;
import com.entity.Brick;
import com.entity.Clock;
import com.entity.Paddle;
import com.observer.Observer;
import com.sounds.Sounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Breakout extends JPanel implements Constants, Observer {

	public Time timer;
	Clock clock;
	private int timerDelta = 10;
	private Ball ball;

	private Paddle paddle;
	private Brick bricks[];
	JLabel timeLbl = new JLabel("Time", JLabel.LEFT);
	public GameController controller;
	private String pattern;
	private Strategy strategy;
	private static PaddleListener paddleListener;
	private static JFrame frame;
	public static Logger logger = LogManager.getLogger(Breakout.class);

	public Breakout(JFrame frame, Time timer) {
		this.frame = frame;
		this.timer = timer;
		setLayout(null);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setBackground(Color.WHITE);
		initializeGame();
		timeLbl.setFont(new Font("Serif", Font.PLAIN, fontSize));
		timeLbl.setBounds(620, 0, 100, 100); // clock position changed
		paddleListener = new PaddleListener(paddle, controller);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, lineWidth, lineHeight);
		g.setColor(Color.BLUE);
		ball.drawGraphic(g);
		g.setColor(Color.BLACK);
		paddle.drawGraphic(g);
		for (Brick b : bricks) {
			b.drawGraphic(g);
		}
		timeLbl.setText(clock.getTime());
		this.add(timeLbl);
		timeLbl.setVisible(true);
	}

	public void initializeGame() {
		this.pattern = "Wedge";
		this.bricks = new Brick[numBricks];
		this.createBricks(this.pattern);
		this.ball = new Ball(ballXPos, ballYPos);
		this.clock = new Clock(timerDelta);
		this.paddle = new Paddle(100, 350);
		timeLbl.setFont(new Font("Serif", Font.PLAIN, fontSize));
		timeLbl.setBounds(620, 0, 100, 100); // clock position changed
		this.controller = new GameController(ball, paddle, bricks, clock, this);
		paddleListener = new PaddleListener(paddle, controller);
		timer.registerObserver(this);
		timer.registerObserver(controller);
		frame.getContentPane().add(this);
		frame.revalidate();
		timer.stopTimer();
		logger.info("Breakout game initialized");
	}
	
	public void createBricks(String type){
		if (type.equals("Square")){
			this.pattern = "Square";
			this.strategy = new SquareStrategy(bricks);
		} else {
			this.pattern = "Wedge";
			this.strategy = new WedgeStrategy(bricks);
			
		}
		this.repaint();
		this.revalidate();
		logger.info("Bricks created");

	}
	
	public void setBrickPattern(String pattern) {
		this.pattern = pattern;
	}
	
	public String getBrickPattern() {
		return this.pattern;
	}
	
	public void reset() {
		for (int i = 0; i < numBricks; i++) {
			bricks[i].setFlag(true);
			bricks[i].reset();
		}
		ball.reset();
		clock.reset();
		// timeLbl.setText(clock.getTime());
		paddle.reset();
		logger.info("Resetting game entities");
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public void update() {
		boolean exit = true;
		for (Brick B : bricks) {
			if (B.isFlag()) {
				exit = false;
				break;
			}
		}
		if (exit) {
			logger.info("Game ended successfully");
			repaint();
			Sounds.WIN.play();
			timer.stopTimer();
			JOptionPane.showMessageDialog(null, "You win!");
		} else {
			repaint();
		}
	}

	public int handlePlayPause() {
		logger.debug("Handling Play pause");
		if (timer.isRunning()) {
			
			frame.removeKeyListener(paddleListener);
			timer.stopTimer();
		} else {
			frame.addKeyListener(paddleListener);
			timer.startTimer();
		}
		return 0;
	}

	public void handleReplay() {
		logger.debug("Handle replay");
		timer.stopTimer();
		frame.removeKeyListener(paddleListener);
		reset();
		try {
			controller.replay();
		} catch (InvocationTargetException | InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	public int loadGame(String path) {
		
		int error_code = 0;
		
		if(new File(path).exists() == false)
		{
			logger.error("File not found for load game");
			error_code = 1;
			return error_code;
		}
			
		
		LoadGame game = new LoadGame();
		try {
			frame.getContentPane().remove(this);
			revalidate();
			ArrayList<MacroCommand> macroCommandList = game.load(path);
			MacroCommand getLastState = macroCommandList.get(macroCommandList.size() - 1);
			this.ball = (Ball) getLastState.ball;
			this.paddle = (Paddle) getLastState.paddle;
			this.clock = (Clock) getLastState.clock;
			this.bricks = (Brick[]) getLastState.bricks;
			this.controller = null;
			this.controller = new GameController(ball, paddle, bricks, clock, this);
			this.paddleListener = new PaddleListener(paddle, controller);
			controller.setMacroCommandList(macroCommandList);
			timer.removeAllObservers();
			timer.registerObserver(this);
			timer.registerObserver(controller);
			frame.addKeyListener(paddleListener);
			frame.getContentPane().add(this);
			frame.repaint();
			frame.revalidate();

		} catch (Exception e) {
			logger.fatal("Loading game failed");
			e.printStackTrace();
		}
		
		return error_code;
	}

	public void saveGame() {
		SaveGame saveGame = new SaveGame();
		try {
			saveGame.save(controller.getMacroCommandList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.fatal("Saving game failed");
			e.printStackTrace();
		}
	}

	public void handleRestart() {
		logger.debug("Handle restart");
		reset();
		controller.clearMacroCommandlist();
		frame.addKeyListener(paddleListener);
	}
	
	public void handleStartGame() {
		logger.debug("Starting game");
		frame.addKeyListener(paddleListener);
	}

	public void handleUndo() {
		logger.debug("Handling undo");
		controller.undo();
	}

	public void handlePattern() {
		logger.debug("Handle Bricks pattern");
		String temp = this.pattern.equals("Wedge") ? "Square" : "Wedge";
		this.pattern = temp;
		this.setBrickPattern(this.pattern);
		this.createBricks(this.pattern);
	}
}