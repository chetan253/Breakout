package entity;

import constants.Constants;

public class Clock implements Constants {

	public long milisecondsElapsed;
	public final int DELTA;

	public Clock(int delta) {
		this.DELTA = delta;
		milisecondsElapsed = 0;
	}

	public void tick(int milisPassed) {
		milisecondsElapsed += milisPassed;
	}

	public String getTime() {
		if (getSeconds() >= 10) {
			return Integer.toString(getMinutes()) + ":" + Integer.toString(getSeconds());
		} else {
			return Integer.toString(getMinutes()) + ":0" + Integer.toString(getSeconds());
		}
	}

	public void reset() {
		milisecondsElapsed = 0;
	}

	private int getMinutes() {
		return (int) (milisecondsElapsed / 60000);
	}

	private int getSeconds() {
		return (int) ((milisecondsElapsed / 1000) % 60);
	}

}
