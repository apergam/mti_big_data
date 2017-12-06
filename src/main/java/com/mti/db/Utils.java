package com.mti.db;

import org.joda.time.LocalDateTime;

public class Utils {

	public Utils() {
		// TODO Auto-generated constructor stub
	}
	
	public static long getCurrentTimeMinusXHours(int hours) {
		if(hours < 0) {
			hours = 0;
		}
		LocalDateTime currentTime = new LocalDateTime().minusHours(hours);
		return currentTime.toDate().getTime();
	}

	public static long getCurrentTimeMinusXMinutes(int minutes) {
		if(minutes < 0) {
			minutes = 0;
		}
		LocalDateTime currentTime = new LocalDateTime().minusMinutes(minutes);
		return currentTime.toDate().getTime();
	}
	
	public static long getCurrentTime() {
		LocalDateTime currentTime = new LocalDateTime();
		return currentTime.toDate().getTime();
	}
	
	public static boolean isNullOrEmpty(String string) {
		return string == null || string.isEmpty();
	}

}
