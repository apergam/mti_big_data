package com.mti.db;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.util.Bytes;

public class ConstantUtils {

	public final static String ENABLED = "Y";
	public final static String DISABLED = "N";
	
	//Table Names
	public final static TableName TABLE_TWEETS = TableName.valueOf("TWEETS");
	
	//Column Family
	public final static String CF_TWEET_DATA = "tweet_data";
	
	//Qualifiers
	public final static byte[] QUALIFIER_TWEET_DATA= Bytes.toBytes("td");
	
	public final static byte[] QUALIFIER_ID = Bytes.toBytes("id");
	public final static byte[] QUALIFIER_TEXT = Bytes.toBytes("text");
	public final static byte[] QUALIFIER_USER = Bytes.toBytes("user");
    public final static byte[] QUALIFIER_TIMESTAMP = Bytes.toBytes("created_at");
    
    public final static byte[] QUALIFIER_HASHTAG_ENTITIES = Bytes.toBytes("hashtag_entities");
    public final static byte[] QUALIFIER_CURRENT_USER_RETWEET_ID = Bytes.toBytes("current_user_retweet_id");
    public final static byte[] QUALIFIER_LATITUDE = Bytes.toBytes("latitude");
    public final static byte[] QUALIFIER_LONGITUDE = Bytes.toBytes("longitude");
    public final static byte[] QUALIFIER_IN_REPLY_TO_SCREEN_NAME = Bytes.toBytes("in_reply_to_screen_name");
    public final static byte[] QUALIFIER_IN_REPLY_TO_STATUS_ID = Bytes.toBytes("in_reply_to_status_id");
    public final static byte[] QUALIFIER_IN_REPLY_TO_USER_ID = Bytes.toBytes("in_reply_to_user_id");
    public final static byte[] QUALIFIER_PLACE = Bytes.toBytes("place");
    public final static byte[] QUALIFIER_QUOTED_STATUS_ID = Bytes.toBytes("quoted_status_id");
    public final static byte[] QUALIFIER_RETWEET_COUNT = Bytes.toBytes("retweet_count");
    public final static byte[] QUALIFIER_SOURCE = Bytes.toBytes("source");
    public final static byte[] QUALIFIER_USER_MENTION_ENTITIES = Bytes.toBytes("user_mention_entities");
    public final static byte[] QUALIFIER_FAVORITE_COUNT = Bytes.toBytes("favorite_count");
    
        
    
}
