package me.Axolotelgamer30.Rising.utils;

public class Timer {
	
	private long prevTime;
	
	public Timer() {
		prevTime = 0;
	}
	
	public boolean hasTimePassed(long miliSec) {
		return (float)(getTime() - prevTime) >= miliSec;
	}
	
	public void reset1() {
		prevTime = getTime();
	}
	
	public static long getTime() {
		return System.nanoTime() / 1000000;
	}
	public long lastMS = System.currentTimeMillis();
	
	public void reset() {
		lastMS = System.currentTimeMillis();
	}
	
	public boolean hasTimeElapsed(long time, boolean reset) {
		if(System.currentTimeMillis()-lastMS > time) {
			if(reset)
				reset();
				return true;
		}
		return false;
	}

}
