package com.UI;

import javax.swing.*;


import com.action.Breakout;
import com.action.Time;
import com.constants.Constants;
import com.controller.GameController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ButtonPanel extends JPanel implements ActionListener, Constants {
	JButton playPauseBtn;
	JButton restartBtn;
	JButton undoBtn;
	JButton replayBtn;
	JButton saveBtn;
	JButton loadBtn;
	JButton changeLayoutBtn;
	JButton chanegPatternBtn;
	private JPanel controlPanel;
	private JFrame frame;
	private int timerDelta = 10;
	private Time timer;
	private Breakout breakoutGame;
	private Strategy strategy;
	private String state;
	private String pattern;
	private int playing;
	private boolean stopReplayFlag;
	public static Logger logger = LogManager.getLogger(ButtonPanel.class);

	public ButtonPanel(JFrame frame, Breakout breakoutGame, Time timer) {
		this.frame = frame;
		controlPanel = new JPanel();
		this.playing = 0; //Not playing
		this.pattern = "Wedge";
		this.state = "Box";
		this.timer = timer;
		playPauseBtn = new JButton("Start"); // Pause & un pause
		playPauseBtn.setActionCommand("Start/Pause/Play");
		playPauseBtn.setFocusable(false);

		restartBtn = new JButton("Restart");
		restartBtn.setActionCommand("Restart");
		restartBtn.setFocusable(false);
		restartBtn.setEnabled(false);

		undoBtn = new JButton("Undo");
		undoBtn.setActionCommand("Undo");
		undoBtn.setFocusable(false);
		undoBtn.setEnabled(false);

		replayBtn = new JButton("Replay");
		replayBtn.setActionCommand("Replay");
		replayBtn.setFocusable(false);
		replayBtn.setEnabled(false);

		saveBtn = new JButton("Save");
		saveBtn.setActionCommand("Save");
		saveBtn.setFocusable(false);
		saveBtn.setEnabled(false);

		loadBtn = new JButton("Load");
		loadBtn.setActionCommand("Load");
		loadBtn.setFocusable(false);
		loadBtn.setEnabled(true);

		changeLayoutBtn = new JButton("Change Layout");
		changeLayoutBtn.setActionCommand("Change Layout");
		changeLayoutBtn.setFocusable(false);
		changeLayoutBtn.setEnabled(true);

		chanegPatternBtn = new JButton("Change Pattern");
		chanegPatternBtn.setActionCommand("Change Pattern");
		chanegPatternBtn.setFocusable(false);
		chanegPatternBtn.setEnabled(true);

		this.controlPanel.setBackground(Color.white);
		this.createUIPanel(this.state);
		playPauseBtn.addActionListener(this);
		restartBtn.addActionListener(this);
		undoBtn.addActionListener(this);
		replayBtn.addActionListener(this);
		saveBtn.addActionListener(this);
		loadBtn.addActionListener(this);
		changeLayoutBtn.addActionListener(this);
		chanegPatternBtn.addActionListener(this);
		this.breakoutGame = breakoutGame;
		logger.info("Buttons initialized");
	}
	
	public void createUIPanel(String type) {
		this.removeAll();
		this.controlPanel = new JPanel();

		if (type.equals("GridBag")) {
			this.state = "GridBag";
			this.strategy = new GridBagStrategy(this, this.controlPanel, playPauseBtn, restartBtn, undoBtn, replayBtn, saveBtn, loadBtn, changeLayoutBtn,chanegPatternBtn);
		} else {
			this.state = "Box";
			this.strategy = new BoxStrategy(this, this.controlPanel, playPauseBtn, restartBtn, undoBtn, replayBtn, saveBtn, loadBtn, changeLayoutBtn,chanegPatternBtn);
		}
		this.controlPanel.setBackground(Color.white);
		this.add(this.controlPanel, BorderLayout.PAGE_END);
		this.repaint();
		this.revalidate();


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand() == "Start/Pause/Play") {
			if(this.playing == 0){
				JButton clickedButton = (JButton) e.getSource();
				clickedButton.setText("Pause");
				this.playing = 1;
				handleStartGame();

			} else if(this.playing == 1 || this.playing == 2){
				JButton clickedButton = (JButton) e.getSource();
				String temp = this.playing == 1 ? "Un-Pause" : "Pause";
				clickedButton.setText(temp);
				this.playing = this.playing == 1 ? 2 : 1;
				handlePlayPause();
			}
		}
		if (e.getActionCommand() == "Restart") {
			handleRestart();
		}
		if (e.getActionCommand() == "Undo") {
			handleUndo();
		}
		if (e.getActionCommand() == "Replay") {
			handleReplay();
		}
		
		if (e.getActionCommand() == "Save") {
			saveGame();
		}
		if (e.getActionCommand() == "Load") {
			timer.stopTimer();
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("."));
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int returnVal = chooser.showOpenDialog(this);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				String path = file.getAbsolutePath();
				loadGame(path);
			}
//			timer.startTimer();

		}
		if (e.getActionCommand() == "Change Layout") {
			String temp = this.state.equals("Box") ? "GridBag" : "Box";
			this.state = temp;
			System.out.println("Change");
			createUIPanel(temp);
			validate();
			repaint();
			logger.info("Layout changed");
		}
		if (e.getActionCommand().equals("Change Pattern")) {
			handlePattern();
			frame.validate();
			frame.repaint();
			logger.info("Brick pattern changed");
		}
		
	}

	public Breakout getGame() {
		return breakoutGame;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	public void handlePlayPause() {
		if (timer.isRunning()) {
			restartBtn.setEnabled(true);
			undoBtn.setEnabled(true);
			replayBtn.setEnabled(true);
			saveBtn.setEnabled(true);
			loadBtn.setEnabled(true);
		} else {
			restartBtn.setEnabled(false);
			undoBtn.setEnabled(false);
			replayBtn.setEnabled(false);
		}
		breakoutGame.handlePlayPause();
	}

	public void handleRestart() {
		breakoutGame.handleRestart();
		this.paintImmediately(0,0,Constants.frameWidth,Constants.frameHeight);
		this.revalidate();
		timer.startTimer();
		playPauseBtn.setText("Start");
		playPauseBtn.setEnabled(true);
		restartBtn.setEnabled(false);
		undoBtn.setEnabled(false);
		replayBtn.setEnabled(false);
		saveBtn.setEnabled(false);
		chanegPatternBtn.setEnabled(true);
		
		this.playing = 0;
		frame.validate();
		frame.repaint();
		timer.stopTimer();
	}

	public void handleStartGame() {
		saveBtn.setEnabled(true);
		loadBtn.setEnabled(true);
		changeLayoutBtn.setEnabled(true);
		chanegPatternBtn.setEnabled(false);
		breakoutGame.handleStartGame();
		timer.startTimer();
	}

	public void handleUndo() {
		breakoutGame.handleUndo();
	}

	public Breakout getBreakOutGame() {
		return this.breakoutGame;
	}

	public void handleReplay() {

//		timer.stopTimer();
		playPauseBtn.setEnabled(true);
		undoBtn.setEnabled(true);
		replayBtn.setEnabled(false);

//		frame.removeKeyListener(breakoutGame.getPaddleListener());
		breakoutGame.handleReplay();
		
	}

	public void loadGame(String path) {
		playPauseBtn.setText("Start");
		playPauseBtn.setEnabled(true);
		playing = 0;
		
		breakoutGame.loadGame(path);
		
	}

	public void saveGame() {
		breakoutGame.saveGame();
	}
	
	public void handlePattern() {
		breakoutGame.handlePattern();
	}

}
