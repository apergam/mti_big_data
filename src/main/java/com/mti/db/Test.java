package com.mti.db;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String args[]) {
		Test test = new Test();
		System.out.println(Utils.getCurrentTimeMinusXHours(1));
		System.out.println(Utils.getCurrentTimeMinusXHours(5));
		System.out.println(Utils.getCurrentTimeMinusXHours(-8));
		
		System.out.println("Minus minutes");
		System.out.println(Utils.getCurrentTimeMinusXMinutes(30));
		System.out.println(Utils.getCurrentTimeMinusXMinutes(120));
		System.out.println(Utils.getCurrentTimeMinusXMinutes(-30));
	
		String fecha  = "Thu Apr 06 15:24:15 +0000 2017";
		String fecha2 = "Wed Dec 06 06:58:57 UTC 2017";
		

		DateFormat format    = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z YYYY", Locale.US);
		DateFormat formatUTC = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz YYYY", Locale.US);
		
		try {
			Date date = format.parse(fecha);
			System.out.println("Date1: " + date);
			
			Date date2 = format.parse(fecha2);
			System.out.println("Date2: " + date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}

