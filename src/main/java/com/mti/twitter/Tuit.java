package com.mti.twitter;

import java.util.Date;

import org.joda.time.DateTime;

import com.mti.db.Utils;

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

	public Tuit(String id, String text, String user, String createdAt, String hashtagEntities, String userMentionEntities,
			String currentUserRetweetId, String latitud, String longitud, String inReplyToScreenName,
			String inReplyToStatusId, String inReplyToUserId, String placeName, String quotedStatusId, String retweetCount,
			String source, String favoriteCount) {
		super();
		
		if(id == null || id == "") {
			this.id = 0;
		}else {
			this.id = Long.valueOf(id);
		}

		this.text = text;
		this.user = user;

		if(Utils.isNullOrEmpty(createdAt)) {
			this.createdAt = null;
		}else {
			this.createdAt = DateTime.parse(currentUserRetweetId).toDate();
		}
		
		this.hashtagEntities = hashtagEntities;
		this.userMentionEntities = userMentionEntities;
		
		if(Utils.isNullOrEmpty(currentUserRetweetId)) {
			this.currentUserRetweetId = 0;
		}else {
			this.currentUserRetweetId = Long.valueOf(currentUserRetweetId);
		}
		
		if(Utils.isNullOrEmpty(latitud)) {
			this.latitud = 0;
		}else {
			this.latitud = Double.valueOf(latitud);
		}
		
		if(Utils.isNullOrEmpty(longitud)){
			this.longitud = 0;
		}else {
			this.longitud = Double.valueOf(longitud);
		}
		
		this.inReplyToScreenName = inReplyToScreenName;
		
		if(Utils.isNullOrEmpty(inReplyToStatusId)) {
			this.inReplyToStatusId = 0;
		}else {
			this.inReplyToStatusId = Long.valueOf(inReplyToStatusId);
		}
		
		if(Utils.isNullOrEmpty(inReplyToUserId)) {
			this.inReplyToStatusId = 0;
		}else {
			this.inReplyToStatusId = Long.valueOf(inReplyToStatusId);
		}
		
		this.placeName = placeName;

		if(Utils.isNullOrEmpty(quotedStatusId)) {
			this.quotedStatusId = 0;
		}else {
			this.quotedStatusId = Long.valueOf(quotedStatusId);
		}
		
		if(Utils.isNullOrEmpty(retweetCount)) {
			this.retweetCount = 0;
		}else {
			this.retweetCount = Long.valueOf(retweetCount);
		}
		
		this.source = source;
		
		if(Utils.isNullOrEmpty(favoriteCount)) {
			this.favoriteCount = 0;
		}else {
			this.favoriteCount = Integer.valueOf(favoriteCount);
		}
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
