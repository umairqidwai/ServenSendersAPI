package com.sevensenders.api.service.helper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.sevensenders.api.service.model.CustomFeed;

public class FeedHelper {

	private static final String ITEM = "item";
	private static final String TITLE = "title";
	private static final String PUB_DATE = "pubDate";
	private static final String FEEDBURNER_ORIGLINK = "origLink";
	private static final String CONTENT_ENCODED = "encoded";
	private static final String IMG = "img";
	private static final String SRC = "src";
	

	/**
	 * Reads XML and return feed
	 * 
	 * @param url
	 * @return
	 */
	public List<CustomFeed> XMLFeedReader(String url) {

		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		List<CustomFeed> customFeedList = new ArrayList<CustomFeed>();
		CustomFeed customFeed = null;
		String title = "";
		Date publishingDate = null;
		String webUrl = "";
		String pictureUrl = "";
		try {
			InputStream in = new URL(url).openStream();
			XMLEventReader reader = xmlInputFactory.createXMLEventReader(in);

			while (reader.hasNext()) {
				XMLEvent nextEvent = reader.nextEvent();

				if (nextEvent.isStartElement()) {
					StartElement startElement = nextEvent.asStartElement();
					switch (startElement.getName().getLocalPart()) {
					case ITEM:
						nextEvent = reader.nextEvent();
						break;
					case TITLE:
						title = extractAttrData(nextEvent, reader);
						break;
					case PUB_DATE:
						String pubDate = extractAttrData(nextEvent, reader);
						publishingDate = new Date(pubDate);
						
						break;
					case FEEDBURNER_ORIGLINK:
						webUrl = extractAttrData(nextEvent, reader);
						break;
					case CONTENT_ENCODED:
						String content = extractAttrData(nextEvent, reader);
						pictureUrl = extractImgUrl(content);
						break;

					}

				} else if (nextEvent.isEndElement()) {
					EndElement endElement = nextEvent.asEndElement();
					if (endElement.getName().getLocalPart().equals(ITEM)) {
						customFeed = new CustomFeed(pictureUrl, title, webUrl, publishingDate);
						customFeedList.add(customFeed);
						nextEvent = reader.nextEvent();
						continue;
					}
				}
			}

		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}

		return customFeedList;

	}

	/**
	 * extract data from attribute
	 * 
	 * @param event
	 * @param reader
	 * @return
	 */
	private String extractAttrData(XMLEvent event, XMLEventReader reader) {
		String data = "";
		try {
			event = reader.nextEvent();
			if (event instanceof Characters) {
				data = event.asCharacters().getData();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * extract Image url from string content
	 * 
	 * @param content
	 * @return
	 */
	private String extractImgUrl(String content) {
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		InputStream in = new ByteArrayInputStream(content.getBytes());

		try {
			XMLEventReader reader = xmlInputFactory.createXMLEventReader(in);
			while (reader.hasNext()) {
				XMLEvent event = reader.nextEvent();
				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();
					switch (startElement.getName().getLocalPart()) {
					case IMG:
						final Iterator iterator = startElement.getAttributes();
						while (iterator.hasNext()) {
							final Attribute attr = (Attribute) iterator.next();
							final QName attrName = attr.getName();
							if (SRC.equals(attrName.getLocalPart())) {
								return attr.getValue();
							}
						}
						break;

					}
				}
			}

		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
