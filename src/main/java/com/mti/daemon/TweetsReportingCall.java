package com.mti.daemon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.protobuf.ServiceException;
import com.mti.db.HBaseUtils;
import com.mti.db.Utils;
import com.mti.twitter.Tuit;


public class TweetsReportingCall implements TweeterActivity {
	private static final Logger logger = LogManager.getLogger();

	public void run() {
		ArrayList<Tuit> tweetsFromHBase = null;
		
		logger.info("Executing Tweets REPORTING");
		
		long tiempoInicial = 0;
		long tiempoFinal = 0;
		int cuenta = 0;

		HBaseUtils hBaseUtils = new HBaseUtils();
		Configuration config = null;
		
		try {
			config = getHBaseConfiguration();
		} catch (IOException | ServiceException e) {
			logger.error("Error getting HBase configuration " + e.getMessage());
			e.printStackTrace();
		}

		try {
			tiempoInicial = Utils.getCurrentTimeMinusXMinutes(30);
			tiempoFinal = Utils.getCurrentTime();
			
			logger.debug("Recuperando tweets de hbase, tiempo Inicial: " + tiempoInicial + ", tiempoFinal: " + tiempoFinal);	
			
			tweetsFromHBase = hBaseUtils.getTweetsBatchFromHBase(config, tiempoInicial, tiempoFinal);
		} catch (IOException e) {
			logger.error("Error retrieving tweets from HBase " + e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("Se recuperaron en total " + tweetsFromHBase.size() + " registros de la BD");
		logger.debug("Se recuperaron en total " + tweetsFromHBase.size() + " registros de la BD");
		
		/*
		for (Iterator<Tuit> iterator = tweetsFromHBase.iterator(); iterator.hasNext();) {
			Tuit t = (Tuit) iterator.next();
			logger.debug(++cuenta + ") Tuit, " + t.toStringSimple());
		}
		*/

	}
	
	private Configuration getHBaseConfiguration() throws IOException, ServiceException {
        Configuration config = null;
        String path = "";

        	config = HBaseConfiguration.create();
            path = this.getClass().getClassLoader().getResource("hbase-site.xml").getPath();

            config.addResource(new Path(path));
            
            HBaseAdmin.checkHBaseAvailable(config);

		return config;
    }

}
