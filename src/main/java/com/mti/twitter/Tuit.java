package com.mti.twitter;

import java.util.Date;

public class Tuit {

    private long id = 0;
    private String text = "";
    private String user = "";
    private Date createdAt;
    private String hashtagEntities = "";
    private String userMentionEntities = "";
    private long currentUserRetweetId = -1L;
    private double latitud = 0;
    private double longitud = 0;
    private String inReplyToScreenName = "";
    private long inReplyToStatusId = 0;
    private long inReplyToUserId = 0;
    private String placeName = "";
    private long quotedStatusId = -1L;
    private long retweetCount = 0;
    private String source = "";
    private int favoriteCount = 0;
    
	public Tuit() {
		
	}

	public Tuit(long id, String text, String user, Date createdAt, String hashtagEntities, String userMentionEntities,
			long currentUserRetweetId, double latitud, double longitud, String inReplyToScreenName,
			long inReplyToStatusId, long inReplyToUserId, String placeName, long quotedStatusId, long retweetCount,
			String source, int favoriteCount) {
		super();
		this.id = id;
		this.text = text;
		this.user = user;
		this.createdAt = createdAt;
		this.hashtagEntities = hashtagEntities;
		this.userMentionEntities = userMentionEntities;
		this.currentUserRetweetId = currentUserRetweetId;
		this.latitud = latitud;
		this.longitud = longitud;
		this.inReplyToScreenName = inReplyToScreenName;
		this.inReplyToStatusId = inReplyToStatusId;
		this.inReplyToUserId = inReplyToUserId;
		this.placeName = placeName;
		this.quotedStatusId = quotedStatusId;
		this.retweetCount = retweetCount;
		this.source = source;
		this.favoriteCount = favoriteCount;
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public String getHashtagEntities() {
		return hashtagEntities;
	}


	public void setHashtagEntities(String hashtagEntities) {
		this.hashtagEntities = hashtagEntities;
	}


	public String getUserMentionEntities() {
		return userMentionEntities;
	}


	public void setUserMentionEntities(String userMentionEntities) {
		this.userMentionEntities = userMentionEntities;
	}


	public long getCurrentUserRetweetId() {
		return currentUserRetweetId;
	}


	public void setCurrentUserRetweetId(long currentUserRetweetId) {
		this.currentUserRetweetId = currentUserRetweetId;
	}


	public double getLatitud() {
		return latitud;
	}


	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}


	public double getLongitud() {
		return longitud;
	}


	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}


	public String getInReplyToScreenName() {
		return inReplyToScreenName;
	}


	public void setInReplyToScreenName(String inReplyToScreenName) {
		this.inReplyToScreenName = inReplyToScreenName;
	}


	public long getInReplyToStatusId() {
		return inReplyToStatusId;
	}


	public void setInReplyToStatusId(long inReplyToStatusId) {
		this.inReplyToStatusId = inReplyToStatusId;
	}


	public long getInReplyToUserId() {
		return inReplyToUserId;
	}


	public void setInReplyToUserId(long inReplyToUserId) {
		this.inReplyToUserId = inReplyToUserId;
	}


	public String getPlaceName() {
		return placeName;
	}


	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}


	public long getQuotedStatusId() {
		return quotedStatusId;
	}


	public void setQuotedStatusId(long quotedStatusId) {
		this.quotedStatusId = quotedStatusId;
	}


	public long getRetweetCount() {
		return retweetCount;
	}


	public void setRetweetCount(long retweetCount) {
		this.retweetCount = retweetCount;
	}


	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
	}


	public int getFavoriteCount() {
		return favoriteCount;
	}


	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

}
