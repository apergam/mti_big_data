package com.mti.daemon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.MasterNotRunningException;
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
		long maxId = 0;

		ArrayList<Cuenta> cuentas = maria.getCuentas();
		List<Status> tweets = new ArrayList<Status>();
		HBaseUtils hBaseUtils = new HBaseUtils();
		UserTimelineLatestTweet userTimelineLatestTweet = new UserTimelineLatestTweet();
		
		for (Cuenta cuenta : cuentas) {
			tweets = userTimelineLatestTweet.getNewTweets(cuenta.getIdCuenta(), cuenta.getMaxId());
			
			cuenta.setTweets(tweets);
			
			
			maxId = userTimelineLatestTweet.getLatestTweetID(cuenta.getIdCuenta());
			cuenta.setMaxId(maxId);
			maria.updateMaxId(cuenta);
			hBaseUtils.putTweetsCuenta(config, cuenta, status);
		}
	}
	
	private void insertTweetsToHBase() throws IOException, ServiceException {
        Configuration config = HBaseConfiguration.create();

        String path = this.getClass().getClassLoader().getResource("hbase-site.xml").getPath();

        config.addResource(new Path(path));

        try {
            HBaseAdmin.checkHBaseAvailable(config);
        } catch (MasterNotRunningException e) {
            System.out.println("HBase is not running." + e.getMessage());
            logger.error("HBase is not running." + e.getMessage());
            return;
        }

        HBaseUtils HBaseClientOperations = new HBaseUtils();
        HBaseClientOperations.run(config);
    }
}
