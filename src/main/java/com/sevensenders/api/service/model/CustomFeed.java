package com.sevensenders.api.service.model;

import java.util.Date;

public class CustomFeed implements Comparable<CustomFeed> {

	private String pictureUrl;
	private String title;
	private String webUrl;
	private Date publishingDate;

	public CustomFeed() {

	}

	public CustomFeed(String pictureUrl, String title, String webUrl, Date publishingDate) {
		super();
		this.pictureUrl = pictureUrl;
		this.title = title;
		this.webUrl = webUrl;
		this.publishingDate = publishingDate;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public Date getPublishingDate() {
		return publishingDate;
	}

	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}

	@Override
	public int compareTo(CustomFeed obj) {
		return getPublishingDate().compareTo(obj.getPublishingDate());
	}

}
