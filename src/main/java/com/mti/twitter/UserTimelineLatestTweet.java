package com.mti.twitter;

import twitter4j.*;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.mti.db.*;

/**
 * @author Santo && Blue Demon
 */
public class UserTimelineLatestTweet {
	private static final Logger logger = LogManager.getLogger("com.mti.twitter");
	
	/**
     * Update latest tweet Id for each official account.
     */
	public void updateLatestTweetID() {
		// Connect to BD, get all official accounts, get last tweet Id for each one, update corresponding record.
		
		MariaDBUtils maria = new MariaDBUtils();
		
		ArrayList<Cuenta> twitterAccounts = maria.getCuentas();
		long tweetID;

		for (Cuenta twitterAccount : twitterAccounts) {
			int success;
			tweetID = getLatestTweetID(twitterAccount.getIdCuenta());
			//logger.debug("Cuenta: " + cuenta.toString());
			System.out.println("LatestTweetID from getLatestTweetID : " + Long.toString(tweetID));
			twitterAccount.setMaxId(tweetID);
			System.out.println("Cuenta: " + twitterAccount.toString());
			success = maria.updateMaxId(twitterAccount);
		}
	}
	
	/**
     * Get new tweets for the twitter account specified.
     */
	public List<Status> getNewTweets(String twitterAccount){
		List<Status> newTweets = new ArrayList<Status>();
		Long tweetID = null;
		
        try {
        	Twitter twitter = new TwitterFactory().getInstance();
            Query query = new Query(twitterAccount);
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                	tweetID = new Long(tweet.getId());
                    logger.debug("Tweet ID: "+ tweetID.toString() + "@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                    newTweets.add(tweet);
                }
            } while ((query = result.nextQuery()) != null);
        } catch (TwitterException te) {
            te.printStackTrace();
            logger.error("Class: updateLatestTweetID Method: getNewTweets(String twitterAccount)" + "Failed to search tweets: " + te.getMessage());
        }
		return newTweets;
	}
	
	/**
     * Get new tweets for the twitter account specified. The tweets are going to be from tweetIDFromDB to latest tweet.
     */
	public List<Status> getNewTweets(String twitterAccount, Long tweetIDFromDB){
		List<Status> newTweets = new ArrayList<Status>();
		Long tweetID = null;
		
        try {
        	Twitter twitter = new TwitterFactory().getInstance();
            Query query = new Query(twitterAccount);
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                	tweetID = new Long(tweet.getId());
                    logger.debug("Tweet ID: "+ tweetID.toString() + "@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                    if(tweetID.compareTo(tweetIDFromDB) == 0) {
                    	break;
                    } else {
                    	newTweets.add(tweet);
                    }
                }
            } while ((query = result.nextQuery()) != null);
        } catch (TwitterException te) {
            te.printStackTrace();
            logger.error("Class: updateLatestTweetID Method: getNewTweets(String twitterAccount)" + "Failed to search tweets: " + te.getMessage());
        }
		return newTweets;
	}
	
	/**
     * Get new tweets for the twitter accounts specified. The tweets are going to be from tweetIDFromDB to latest tweet.
     */
	public List<Status> getNewTweets(ArrayList<Cuenta> twitterAccounts){
		List<Status> newTweets = new ArrayList<Status>();
		List<Status> tmpnewTweets = new ArrayList<Status>();
		
		for (Cuenta twitterAccount : twitterAccounts) {
			if(twitterAccount.getMaxId() != 0) {
				// It isn't the first time for retrieving tweets
				Long tmpID = new Long(twitterAccount.getMaxId());
				tmpnewTweets = getNewTweets(twitterAccount.getIdCuenta(), tmpID);
				for (Status tweet : tmpnewTweets) {
                    logger.debug("getNewTweets(ArrayList<Cuenta> twitterAccounts)" + "@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                    newTweets.add(tweet);
                }
				
			} else {
				// It is the first time for retrieving tweets
				tmpnewTweets = getNewTweets(twitterAccount.getIdCuenta());
				for (Status tweet : tmpnewTweets) {
                    logger.debug("getNewTweets(ArrayList<Cuenta> twitterAccounts)" + "@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                    newTweets.add(tweet);
                }
			}
		}
		updateLatestTweetID();
		return newTweets;
	}
	
	/**
     * Get latest tweet ID for the twitter account specified.
     */
	public long getLatestTweetID(String twitterAccount) {
		long outTweetID = 0;
		
		if (twitterAccount != null) {
	        try {
	        	// get twitter instance with default credentials
				Twitter twitter = new TwitterFactory().getInstance();
				User user = twitter.verifyCredentials();
				
				Paging paging = new Paging(1,1);
				List<Status> statuses = twitter.getHomeTimeline(paging);
	            System.out.println("Showing @" + user.getScreenName() + "'s home timeline.");
            	//logger.debug("Showing @" + user.getScreenName() + "'s home timeline." "@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
	            for (Status status : statuses) {
	                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
	                outTweetID = status.getId();
	            }
				
			
	        } catch (TwitterException te) {
	            te.printStackTrace();
	            logger.error("Failed to search tweets: " + te.getMessage());
	        }
			
        } else {
        	logger.error("java twitter4j.examples.search.SearchTweets [query]");
        }
		
		return outTweetID;
	}
    
	/**
     * @param args
     */
    public static void main(String[] args) {
        
    }
}
