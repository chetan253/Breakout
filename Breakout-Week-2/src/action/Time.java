package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import observer.Observable;
import observer.Observer;

public class Time implements Observable {
	private Timer timer;
	private int timerDelta = 10;
	ArrayList<Observer> observers = new ArrayList<>();

	public Time(Breakout game) {

		ActionListener actionPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				notifyObservers();
			}
		};

		timer = new Timer(timerDelta, (ActionListener) actionPerformer);
		startTimer();
	}

	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(observers.indexOf(o));
	}

	@Override
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update();
		}
	}

	public void startTimer() {
		timer.start();
	}

	public void stopTimer() {
		timer.stop();
	}

	public void restartTimer() {
		timer.restart();
	}

	public boolean isRunning() {
		return timer.isRunning();
	}
}
