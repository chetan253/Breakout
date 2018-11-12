package action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import commands.ClockTick;
import commands.MacroCommand;
import commands.PaddleMoveLeft;
import commands.PaddleMoveRight;
import constants.Constants;
import controller.GameController;
import entity.Ball;
import entity.Brick;
import entity.Clock;
import entity.Paddle;
import observer.Observer;
import sounds.Sounds;

public class Breakout extends JPanel implements Constants, Observer {

	private int timerDelta = 10;
	public Time timer;
	Clock clock;
	ClockTick clockTick;
	private int brickX = 300;
	private int brickY = 50;
	private int aliveBricks;
	private Ball ball;
	private Paddle paddle;
	private Brick bricks[];
	private boolean restart_flag = false;
	protected PaddleMoveRight moveRight;
	protected PaddleMoveLeft moveLeft;
	// public static boolean exit_flag = false;
	JLabel timeLbl = new JLabel("Time", JLabel.LEFT);
	private GameController controller;
	private static PaddleListener paddleListener;
	JButton b1 = new JButton();
	JButton b2 = new JButton();
	JButton b3 = new JButton();
	JButton b4 = new JButton();

	private static JFrame f;

	public Breakout() {
		aliveBricks = numBricks;
		bricks = new Brick[numBricks];
		for (int i = 0; i < numBricks; i++) {
			bricks[i] = new Brick(brickX, brickY);
			bricks[i].setFlag(true);
			brickY = brickY + 75;
		}

		ball = new Ball(ballXPos, ballYPos);
		clock = new Clock(timerDelta);
		paddle = new Paddle(100, 350, 30, ball);
		controller = new GameController(ball, paddle, bricks, clock, this);
		timer = new Time(this);
		timer.startTimer();
		timer.registerObserver(controller);
		timer.registerObserver(this);
		paddleListener = new PaddleListener(paddle, controller);
//		moveRight = new PaddleMoveRight(paddle);
//		moveLeft = new PaddleMoveLeft(paddle);
		setLayout(null);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setBackground(Color.WHITE);
		timeLbl.setFont(new Font("Serif", Font.PLAIN, fontSize));
		timeLbl.setBounds(620, 0, 100, 100); // clock position changed
		b1.setSize(100, 100);
		b1.setVisible(true);
		b1.setFocusable(false);
		b1.setText("Pause/Play");
		b2.setSize(200, 100);
		b2.setVisible(true);
		b2.setText("Restart");
		b3.setSize(300, 100);
		b3.setVisible(true);
		b3.setText("Undo");
		b4.setSize(300, 100);
		b4.setVisible(true);
		b4.setText("Replay");
		b1.setBounds(610, 120, 100, 30);
		b2.setBounds(610, 160, 100, 30);
		b3.setBounds(610, 200, 100, 30);
		b4.setBounds(610, 240, 100, 30);
		b1.setLayout(null);
		b2.setLayout(null);
		b3.setLayout(null);
		b4.setLayout(null);
		b2.setEnabled(false);
		b3.setEnabled(false);
		b4.setEnabled(false);
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		b1.setFocusable(false);
		b2.setFocusable(false);
		b3.setFocusable(false);
		b4.setFocusable(false);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (timer.isRunning()) {
					timer.stopTimer();
					b2.setEnabled(true);
					b3.setEnabled(true);
					b4.setEnabled(true);
				} else {
					f.addKeyListener(paddleListener);
					timer.startTimer();
					b2.setEnabled(false);
					b3.setEnabled(false);
					b4.setEnabled(false);
				}
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restart_flag = true;
				controller.getMacroCommandList().clear();
				f.addKeyListener(paddleListener);
				b1.setEnabled(true);
				b2.setEnabled(false);
				b3.setEnabled(false);
				b4.setEnabled(false);
				initialize();
			}
		});

		b3.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// intentionally left empty
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// intentionally left empty

			}

			@Override
			public void keyPressed(KeyEvent e) {
				controller.undo();
			}
		});
		b4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timer.stopTimer();
				b1.setEnabled(false);
				b3.setEnabled(false);
				f.removeKeyListener(paddleListener);
				try {
					controller.replay();

				} catch (InvocationTargetException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});

	}

	public void initialize() {
		aliveBricks = numBricks;
		for (int i = 0; i < numBricks; i++) {
			bricks[i].setFlag(true);
			bricks[i].reset();
		}
		ball.reset();
		clock.reset();
		timeLbl.setText(clock.getTime());
		paddle.reset();
		if (restart_flag) {
			timer.startTimer();
			restart_flag = false;
		}
	}

	public void draw() {
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawLine(0, 0, 600, 0);
		g.drawLine(0, 400, 600, 400);
		g.drawLine(600, 0, 600, 400);
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

	public static void main(String args[]) throws IOException {
		Breakout breakout = new Breakout();
		f = new JFrame();
		f.add(breakout);
		f.setVisible(true);
		f.setSize(frameWidth, frameHeight);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setFocusable(true);
		f.addKeyListener(paddleListener);
		f.setTitle("Moving Ball");
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void update() {
		boolean exit = true;
		for (Brick B : bricks) {
			if (B.isFlag()) {
				exit = false;
				break;
			}
		}
		if (exit) {
			repaint();
			Sounds.WIN.play();
			timer.stopTimer();
			JOptionPane.showMessageDialog(null, "You win!");
			b1.setEnabled(false);
			b4.setEnabled(true);
		} else {
			repaint();
		}
	}
}
