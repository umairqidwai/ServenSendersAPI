package com.sevensenders.api.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sevensenders.api.service.model.CustomFeed;
import com.sevensenders.api.service.model.WebcomicXKCD;
import com.sevensenders.api.service.repo.ServiceRepository;

@org.springframework.stereotype.Service
public class Service {

	@Autowired
	private ServiceRepository serviceRepository;

	public List<CustomFeed> processedFeed(String PDLUrl, String webComicUrl) {
		List<CustomFeed> customFeedList = serviceRepository.fetchPDLFeeds(PDLUrl);
		WebcomicXKCD webComicXKCD = serviceRepository.fetchXKCD(webComicUrl).getBody();
		int year = Integer.valueOf(webComicXKCD.getYear());
		int month = Integer.valueOf(webComicXKCD.getMonth());
		int day = Integer.valueOf(webComicXKCD.getDay());
		String dateString = webComicXKCD.getDay() + "/" + webComicXKCD.getMonth() + "/" + webComicXKCD.getYear();
		;
		CustomFeed customFeed = new CustomFeed(webComicXKCD.getImg(), webComicXKCD.getTitle(), webComicUrl,
				new Date(year - 1900, month, day));
		customFeedList.add(customFeed);
		Collections.sort(customFeedList);
		return customFeedList;
	}

}
