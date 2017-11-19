package com.mti.daemon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TweetsReportingCall implements TweeterActivity {
	private static final Logger logger = LogManager.getLogger();

	public void run() {
		logger.info("Executing Tweets REPORTING");
		
	}

}
