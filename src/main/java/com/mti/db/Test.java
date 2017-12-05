package com.mti.db;

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
		
	}
	

}

