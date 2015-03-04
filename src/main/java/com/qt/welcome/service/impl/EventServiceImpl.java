package com.qt.welcome.service.impl;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import com.qt.welcome.service.EventService;

public class EventServiceImpl implements EventService {

	@Override
	public String findEvent(String xmltext) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(xmltext);
			InputSource is = new InputSource(sr);
			Document document = db.parse(is);

			Element root = document.getDocumentElement();
			String result = root.getElementsByTagName("Event").item(0).getTextContent();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public String[] subscribeXML(String xmltext) {
		String[] result = new String[5];
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(xmltext);
			InputSource is = new InputSource(sr);
			Document document = db.parse(is);

			Element root = document.getDocumentElement();
			result[0] = "0";
			result[1] = root.getElementsByTagName("ToUserName").item(0).getTextContent();
			result[2] = root.getElementsByTagName("FromUserName").item(0).getTextContent();
			result[3] = root.getElementsByTagName("CreateTime").item(0).getTextContent();
			result[4] = root.getElementsByTagName("EventKey").item(0).getTextContent();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String[] unsubscribeXML() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] ticketXML(String xmltext) {
		String[] result = new String[6];
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(xmltext);
			InputSource is = new InputSource(sr);
			Document document = db.parse(is);

			Element root = document.getDocumentElement();
			result[0] = "0";
			result[1] = root.getElementsByTagName("ToUserName").item(0).getTextContent();
			result[2] = root.getElementsByTagName("FromUserName").item(0).getTextContent();
			result[3] = root.getElementsByTagName("CreateTime").item(0).getTextContent();
			result[4] = root.getElementsByTagName("EventKey").item(0).getTextContent();
			result[5] = root.getElementsByTagName("Ticket").item(0).getTextContent();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String[] locationXML() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] clickXML() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] viewXML() {
		// TODO Auto-generated method stub
		return null;
	}

}
