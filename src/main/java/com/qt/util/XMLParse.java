/**
 * 对公众平台发送给公众账号的消息加解密.
 * 
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 */

// ------------------------------------------------------------------------

package com.qt.util;

import java.io.StringReader;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * XMLParse class
 *
 * 提供提取消息格式中的密文及生成回复消息格式的接口.
 */
class XMLParse {

	/**
	 * 提取出xml数据包中的加密消息
	 * @param xmltext 待提取的xml字符串
	 * @return 提取出的加密消息字符串
	 * @throws AesException 
	 */
	public static Object[] extract(String xmltext) throws AesException     {
		Object[] result = new Object[3];
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(xmltext);
			InputSource is = new InputSource(sr);
			Document document = db.parse(is);

			Element root = document.getDocumentElement();
			NodeList nodelist1 = root.getElementsByTagName("Encrypt");
			NodeList nodelist2 = root.getElementsByTagName("ToUserName");
			result[0] = 0;
			result[1] = nodelist1.item(0).getTextContent();
			result[2] = nodelist2.item(0).getTextContent();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new AesException(AesException.ParseXmlError);
		}
	}

	/**
	 * 生成xml消息
	 * @param encrypt 加密后的消息密文
	 * @param signature 安全签名
	 * @param timestamp 时间戳
	 * @param nonce 随机字符串
	 * @return 生成的xml字符串
	 */
	public static String generate(String encrypt, String signature, String timestamp, String nonce) {

		String format = "<xml>\n" + "<Encrypt><![CDATA[%1$s]]></Encrypt>\n"
				+ "<MsgSignature><![CDATA[%2$s]]></MsgSignature>\n"
				+ "<TimeStamp>%3$s</TimeStamp>\n" + "<Nonce><![CDATA[%4$s]]></Nonce>\n" + "</xml>";
		return String.format(format, encrypt, signature, timestamp, nonce);

	}
	
	/**
	 * 生成回复xml消息
	 * @param toUserName 接收方帐号（收到的OpenID）
	 * @param fromUserName 开发者微信号
	 * @param createTime 消息创建时间 （整型）
	 * @param msgType 消息类型
	 * @param content 回复的消息内容
	 * @return 生成的xml字符串
	 */
	public static String textRe(String toUserName, String fromUserName, String createTime, String msgType, String content) {
		String format = "<xml>\n" + "<ToUserName><![CDATA[%1$s]]></ToUserName>\n"
				+ "<FromUserName><![CDATA[%2$s]]></FromUserName>\n"
				+ "<CreateTime>%3$s</CreateTime>\n" + "<MsgType><![CDATA[%4$s]]></MsgType>\n" 
				+ "<Content><![CDATA[%5$s]]></Content>\n" + "</xml>";
		return String.format(format, toUserName, fromUserName, createTime, msgType, content);
	}
	
	/**
	 * 生成回复图文xml消息
	 * @param map 传入参数集合
	 * @return 生成的xml字符串
	 */
	public static String textImageRe(Map<String,String> map) {
		String format = "<xml>\n" + "<ToUserName><![CDATA[%1$s]]></ToUserName>\n"
				+ "<FromUserName><![CDATA[%2$s]]></FromUserName>\n"
				+ "<CreateTime>%3$s</CreateTime>\n" + "<MsgType><![CDATA[%4$s]]></MsgType>\n" 
				+ "<ArticleCount><![CDATA[%5$s]]></ArticleCount>\n" + "<Articles>\n<item>" 
				+ "<Title><![CDATA[%6$s]]></Title>\n" + "<Description><![CDATA[%7$s]]></Description>\n" 
				+ "<PicUrl><![CDATA[%8$s]]></PicUrl>\n" + "<Url><![CDATA[%9$s]]></Url>\n" 
				+ "</item>\n</Articles>\n</xml>";
		return String.format(format, map.get("ToUserName"), map.get("FromUserName"), 
				map.get("CreateTime"), map.get("MsgType"), map.get("ArticleCount"), 
				map.get("Title"), map.get("Description"), map.get("PicUrl"), map.get("Url"));
	}
	
	/**
	 * 提取出用户传值xml数据内容
	 * @param xmltext 待提取的xml字符串
	 * @return 提取出的字符串数组
	 * @throws AesException 
	 */
	public static Object[] extractAll(String xmltext) throws AesException     {
		Object[] result = new Object[7];
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(xmltext);
			InputSource is = new InputSource(sr);
			Document document = db.parse(is);

			Element root = document.getDocumentElement();
			result[0] = 0;
			result[1] = root.getElementsByTagName("ToUserName").item(0).getTextContent();
			result[2] = root.getElementsByTagName("FromUserName").item(0).getTextContent();
			result[3] = root.getElementsByTagName("CreateTime").item(0).getTextContent();
			result[4] = root.getElementsByTagName("MsgType").item(0).getTextContent();
			result[5] = root.getElementsByTagName("Content").item(0).getTextContent();
			result[6] = root.getElementsByTagName("MsgId").item(0).getTextContent();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new AesException(AesException.ParseXmlError);
		}
	}
	
	/**
	 * 提取出xml数据类型
	 * @param xmltext 待提取的xml字符串
	 * @return 提取出的类型结果
	 * @throws AesException 
	 */
	public static String msgType(String xmltext) throws AesException     {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(xmltext);
			InputSource is = new InputSource(sr);
			Document document = db.parse(is);

			Element root = document.getDocumentElement();
			String result = root.getElementsByTagName("MsgType").item(0).getTextContent();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new AesException(AesException.ParseXmlError);
		}
	}
}
