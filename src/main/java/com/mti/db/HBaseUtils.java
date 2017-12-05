/**
 * 25/11/17
 * apergam
 * Added remaining tweet fields to the put object.
 * -----
 */
package com.mti.db;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mti.twitter.Tuit;

import twitter4j.Status;

public class HBaseUtils {
	private static final Logger logger = LogManager.getLogger("com.mti.db");
	Configuration config = null;    

    public void putTweetsCuenta(Configuration config, Cuenta cuenta, List<Status> tweets ) throws IOException {
        
        try (Connection connection = ConnectionFactory.createConnection(config)) {

            Table table = connection.getTable(ConstantUtils.TABLE_TWEETS);
            Put p = null;
            
            //byte[] rowKey = Bytes.toBytes("0");
            String rowKey = "";
            
            for (Status tweet : tweets) {
				rowKey = tweet.getId() + "#" + tweet.getCreatedAt();
				
				logger.debug("PUT inserting rowKey: " + rowKey  + 
						", id: " + tweet.getId() +
						", user: " + tweet.getUser().getScreenName() +
						", timeStamp: " + tweet.getCreatedAt()
						
						);
				
				p = new Put(rowKey.getBytes());
				
	            p.addImmutable(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_ID, Long.toString(tweet.getId()).getBytes());
	            p.addImmutable(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_TEXT, tweet.getText().getBytes());
	            p.addImmutable(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_USER, tweet.getUser().getName().getBytes());
	            p.addImmutable(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_TIMESTAMP, tweet.getCreatedAt().toString().getBytes());

	            p.addImmutable(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_HASHTAG_ENTITIES, Arrays.toString(tweet.getHashtagEntities()).getBytes());
	            p.addImmutable(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_USER_MENTION_ENTITIES, Arrays.toString(tweet.getUserMentionEntities()).getBytes());
	            
	            
        		p.addImmutable(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_CURRENT_USER_RETWEET_ID, Long.toString(tweet.getCurrentUserRetweetId()).getBytes()); 
        		
        		if(tweet.getGeoLocation() != null) {
        			p.addImmutable(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_LATITUDE, Double.toString(tweet.getGeoLocation().getLatitude()).getBytes());
            		p.addImmutable(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_LONGITUDE, Double.toString(tweet.getGeoLocation().getLongitude()).getBytes());	
        		}
        		
        		if(tweet.getInReplyToScreenName() != null) {
        			p.addImmutable(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_IN_REPLY_TO_SCREEN_NAME, tweet.getInReplyToScreenName().getBytes());
        		}
        		
        		p.addImmutable(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_IN_REPLY_TO_STATUS_ID, Long.toString(tweet.getInReplyToStatusId()).getBytes());
        		p.addImmutable(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_IN_REPLY_TO_USER_ID, Long.toString(tweet.getInReplyToUserId()).getBytes());

        		if(tweet.getPlace() != null) {
        			p.addImmutable(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_PLACE, (tweet.getPlace().getName()).getBytes());	
        		}
        		
        		p.addImmutable(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_QUOTED_STATUS_ID, Long.toString(tweet.getQuotedStatusId()).getBytes());
        		p.addImmutable(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_RETWEET_COUNT, Long.toString(tweet.getRetweetCount()).getBytes());
        	
        		if(tweet.getSource() != null) {
        			p.addImmutable(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_SOURCE, tweet.getSource().getBytes());	
        		}
        		
        		
        		p.addImmutable(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_FAVORITE_COUNT, Long.toString(tweet.getFavoriteCount()).getBytes());

	            table.put(p);
			}
        }
    }


    public void run(Configuration config) throws IOException {}

    
    private ArrayList <Tuit> getTweetsBatchFromHBase(Configuration config, long timestampInicial, long timestampFinal) throws IOException {

    	ArrayList<Tuit> tweetsFromHBase = new ArrayList<Tuit>();
    	
    	try (Connection connection = ConnectionFactory.createConnection(config)) {

    		Table table = connection.getTable(ConstantUtils.TABLE_TWEETS);

    		Scan scan = new Scan();
    		scan.addColumn(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_ID);
    		scan.addColumn(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_TEXT);
    		scan.addColumn(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_USER);
    		scan.addColumn(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_TIMESTAMP);
    		scan.addColumn(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_HASHTAG_ENTITIES);
    		scan.addColumn(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_USER_MENTION_ENTITIES);
    		scan.addColumn(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_CURRENT_USER_RETWEET_ID); 
    		scan.addColumn(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_LATITUDE);
    		scan.addColumn(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_LONGITUDE);
    		scan.addColumn(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_IN_REPLY_TO_SCREEN_NAME);
    		scan.addColumn(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_IN_REPLY_TO_STATUS_ID);
    		scan.addColumn(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_IN_REPLY_TO_USER_ID);
    		scan.addColumn(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_PLACE);
    		scan.addColumn(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_QUOTED_STATUS_ID);
    		scan.addColumn(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_RETWEET_COUNT);
    		scan.addColumn(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_SOURCE);	
    		scan.addColumn(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_FAVORITE_COUNT);

    		try (ResultScanner scanner = table.getScanner(scan)) {
    			
    			for (Result result : scanner) {
    				
    				logger.debug("Found row: " + result.getRow());
    				
    				Tuit tuit = new Tuit(
    						new String(result.getValue(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_ID)),
    						new String(result.getValue(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_TEXT)),
    						new String(result.getValue(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_USER)),
    						new String(result.getValue(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_TIMESTAMP)),
    						new String(result.getValue(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_HASHTAG_ENTITIES)), 
    						new String(result.getValue(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_USER_MENTION_ENTITIES)),
    						new String(result.getValue(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_CURRENT_USER_RETWEET_ID)), 
    						new String(result.getValue(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_LATITUDE)),
    						new String(result.getValue(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_LONGITUDE)), 
    						new String(result.getValue(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_IN_REPLY_TO_SCREEN_NAME)), 
    						new String(result.getValue(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_IN_REPLY_TO_STATUS_ID)),
    						new String(result.getValue(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_IN_REPLY_TO_USER_ID)),
    						new String(result.getValue(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_PLACE)), 
    						new String(result.getValue(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_QUOTED_STATUS_ID)), 
    						new String(result.getValue(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_RETWEET_COUNT)), 
    						new String(result.getValue(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_SOURCE))	, 
    						new String(result.getValue(ConstantUtils.CF_TWEET_DATA.getBytes(), ConstantUtils.QUALIFIER_FAVORITE_COUNT))
    						);
    				
    				tweetsFromHBase.add(tuit);

    			}
    		}

    		logger.debug("Total tweets retrieved: " + tweetsFromHBase.size());
    	}
    	return tweetsFromHBase;
    }
    


/*    
    private void connect() throws IOException, ServiceException {
        config = HBaseConfiguration.create();

        String path = this.getClass().getClassLoader().getResource("hbase-site.xml").getPath();

        config.addResource(new Path(path));

        try {
            HBaseAdmin.checkHBaseAvailable(config);
        } catch (MasterNotRunningException e) {
            System.out.println("HBase is not running." + e.getMessage());
            return;
        }
    }
    */
}