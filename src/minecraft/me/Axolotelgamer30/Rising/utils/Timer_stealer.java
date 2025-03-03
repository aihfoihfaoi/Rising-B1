package me.Axolotelgamer30.Rising.utils;


public class Timer_stealer {
	public static short convert(float perSecond) {
		return (short) (1000 / perSecond);
	}

	public static long getCurrentTime() {
		return System.nanoTime() / 1000000;
	}

	private long previousTime;

	public Timer_stealer() {
		previousTime = -1L;
	}

	public long get() {
		return previousTime;
	}

	public boolean check(float milliseconds) {
		return Timer_stealer.getCurrentTime() - previousTime >= milliseconds;
	}

	public void reset() {
		previousTime = Timer_stealer.getCurrentTime();
	}
}