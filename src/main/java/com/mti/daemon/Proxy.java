package com.mti.daemon;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Proxy {

	private static final Map<String, String> ACTIVITIES =
	        new HashMap<String, String>();
	    static{
	    	ACTIVITIES.put("LOAD", "com.mti.daemon.TweetsLoadCall");
	    	ACTIVITIES.put("PROCESS", "com.mti.daemon.TweetsProcessingCall");
	    	ACTIVITIES.put("REPORT", "com.mti.daemon.TweetsReportingCall");
	    }
	    
	    public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
	    	final Logger logger = LogManager.getLogger();

	    	if(args.length < 1){
	    		logger.fatal("Missing parameter, the activity parameter has not be found");
	    		throw new IllegalArgumentException("Missing parameter, the activity parameter has not be found");
	    		
	    	}else if(!ACTIVITIES.containsKey(args[0])) {
	    		logger.fatal("Invalid activity, the provided parameter <" + args[0] +"> is not a valid action");
	    		throw new IllegalArgumentException("Invalid activity, the provided parameter is not a valid action");
	    		
	    	}else {
	    		logger.debug("Activity to be executed: " + args[0]);

	    		Class<?> activityClass;
	    		try {
	    			activityClass = Class.forName(ACTIVITIES.get(args[0]));
	    			
	    			Object activity = activityClass.newInstance();
	    			
	    			Method runMethod = activity.getClass().getMethod("run", null);
	    			runMethod.invoke(activity, null);
	    		} catch (Exception e1) {
	    			logger.error("Error executing the activity " + args[0] + ", " + e1.getMessage());
	    			e1.printStackTrace();
	    		}
	    	}
	    }
}