package com.mti.daemon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.protobuf.ServiceException;
import com.mti.db.Cuenta;
import com.mti.db.HBaseUtils;
import com.mti.db.MariaDBUtils;
import com.mti.twitter.UserTimelineLatestTweet;
import twitter4j.Status;

public class TweetsLoadCall implements TweeterActivity {
	private static final Logger logger = LogManager.getLogger();

	public void run() {
		logger.info("Executing Tweets LOAD");
		
		MariaDBUtils maria = new MariaDBUtils();
		String maxId = "";

		List<Status> tweets = new ArrayList<Status>();
		HBaseUtils hBaseUtils = new HBaseUtils();
		
		UserTimelineLatestTweet userTimelineLatestTweet = new UserTimelineLatestTweet();

		Configuration config = null;
		try {
			config = getHBaseConfiguration();
		} catch (IOException | ServiceException e) {
			logger.error("Error getting HBase configuration " + e.getMessage());
			e.printStackTrace();
		}

		
		ArrayList<Cuenta> cuentas = maria.getCuentas();
		for (Cuenta cuenta : cuentas) {
			tweets = userTimelineLatestTweet.getNewTweets(cuenta.getIdCuenta(), cuenta.getMaxId());
			
			cuenta.setTweets(tweets);
			
			maxId = userTimelineLatestTweet.getLatestTweetID(cuenta.getIdCuenta());
			cuenta.setMaxId(maxId);
			maria.updateMaxId(cuenta);
			
			logger.debug("Aqui deberia de insertar en hbase");

			try {
				hBaseUtils.putTweetsCuenta(config, cuenta, tweets);
			} catch (IOException e) {
				logger.error("Error inserting tweets in HBase " + e.getMessage());
				e.printStackTrace();
			}

		}
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
