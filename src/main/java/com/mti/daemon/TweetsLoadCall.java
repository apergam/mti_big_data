package com.mti.daemon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TweetsLoadCall implements TweeterActivity {
	private static final Logger logger = LogManager.getLogger();

	/*
	public static void main(String[] args) {

		logger.info("Hello, World!");
		logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
        logger.fatal("This is a fatal message");

	}
	*/

	public void run() {
		logger.info("Executing Tweets LOAD");
		
	}
}
