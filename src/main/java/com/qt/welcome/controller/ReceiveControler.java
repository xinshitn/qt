package com.qt.welcome.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.qt.functionsPage.service.QrInfoService;
import com.qt.quartz.service.QuartzJobService;
import com.qt.util.AesException;
import com.qt.util.WXBizMsgCrypt;
import com.qt.util.WriterLog;
import com.qt.welcome.service.EventService;
import com.qt.welcome.service.impl.EventServiceImpl;

@Controller
public class ReceiveControler {
	
	private WXBizMsgCrypt wxbiz;
	@Autowired
    private QuartzJobService quartzJobService;
	@Autowired
	private QrInfoService qrInfoService;
	
	//签名串
	private String signature;
	//时间戳
	private String timestamp;
	//随机数
	private String nonce;
	//随机字符串（验证微信服务器用）
	private String echostr;
	
	//接收的用户消息
	private String receive;
	
	//返回值
	private String result;
	private InputStream inStream;
	
	public void setWxbiz(WXBizMsgCrypt wxbiz) {
		this.wxbiz = wxbiz;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}
	
	public String getResult() {
		return result;
	}
	public InputStream getInStream() {
		return inStream;
	}
	



	public String receiveMsg(){
		//URL:http://qtwxbp.duapp.com/receiveMsg
		try {
			wxbiz = new WXBizMsgCrypt("qt001", quartzJobService.selectWxTicket("EncodingAESKey"), quartzJobService.selectWxTicket("appID"));
		} catch (AesException e) {
			System.out.println("消息接收初始化失败！");
		}
		if(echostr == null || echostr.equals("")){
			StringBuilder sb = new StringBuilder();;
			try {
				HttpServletRequest request = ServletActionContext.getRequest();
				BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));  
				String line = null;  
		        while((line = br.readLine())!=null){  
		            sb.append(line);  
		        }
				receive = wxbiz.decryptMsg(signature, timestamp, nonce, sb.toString());//解密，返回用户消息明文
				String msg = choiceMsg();//生成自动回复内容
				result = wxbiz.encryptMsg(msg, timestamp, nonce);//加密返回结果
			} catch (AesException e) {
				WriterLog.writerLog("解密数据失败！ Decrypt the data failed");
				try {//尝试不加密
					receive = sb.toString();
					result = choiceMsg();//生成自动回复内容
				} catch (AesException e1) {
				}
			} catch (IOException e) {
				WriterLog.writerLog("数据读取异常！ Data read exception");
			} 
		}else{
			try {
				result = wxbiz.verifyUrl(signature, timestamp, nonce, echostr);
			} catch (AesException e) {
				WriterLog.writerLog("合法性验证失败！ Validation failed");
			}
		}
		try {
			if(result==null)result="";
			//定义返回流(将返回给微信)
			inStream = new ByteArrayInputStream(result.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	//判断用户发送的消息并生成自动回复消息
	private String choiceMsg() throws AesException{
		String msgType = wxbiz.msgType(receive);//获取消息类型
		//初始化向用户发送的信息参数
		String msg = "";
		if(msgType.equalsIgnoreCase("text")){//用户发送文本消息
			//获得用户发送的消息
			Object[] encrypt = wxbiz.readXml(receive);
			//生成回复消息
			msg = reMsg(wxbiz,encrypt,"");
		} else if("event".equalsIgnoreCase(msgType)){//接收事件
			EventService event = new EventServiceImpl();
			String eventType = event.findEvent(receive);
			if("subscribe".equalsIgnoreCase(eventType)){//新用户订阅
				String[] subInfo = event.subscribeXML(receive);
				if(subInfo[4]!=null&&!"".equals(subInfo[4])){
					subInfo[4] = subInfo[4].replace("qrscene_", "");
					msg = reMsg(wxbiz,subInfo,"1");
				}else{
					msg = reMsg(wxbiz,subInfo,"0");
				}
			} else if("SCAN".equalsIgnoreCase(eventType)){//二维码
				String[] subInfo = event.ticketXML(receive);
				msg = reMsg(wxbiz,subInfo,"1");
			}
		}
		return msg;
	}
	//生成自动回复消息
	private String reMsg(WXBizMsgCrypt wxbiz, Object[] encrypt,String option){
		if("0".equals(option)){//图文消息参数
			return wxbiz.textImageRe(textImageMap(encrypt));
		}else if("1".equals(option)){//二维码消息
			String sid = encrypt[4].toString().trim();
			WriterLog.writerLog(sid);
			String info = qrInfoService.searchQrInformation(sid);
			if(info==null)info="内容为空";
			return wxbiz.textRe(encrypt[2].toString(), encrypt[1].toString(), timestamp, "text", "二维码内容："+info);
		}
		String userMsg = encrypt[5].toString();
		if("1".equals(userMsg)){//用户发送的消息为 1
			return wxbiz.textImageRe(textImageMap(encrypt));
//			return wxbiz.textRe(encrypt[2].toString(), encrypt[1].toString(), timestamp, "text", "<a href=\"http://qtwxbp.duapp.com/myQt_index/\">oo清汀驿站oo</a>");
		}else if("0".equals(userMsg)){
			return wxbiz.textRe(encrypt[2].toString(), encrypt[1].toString(), timestamp, "text", menu());
		}else if("2".equals(userMsg)){
			return wxbiz.textRe(encrypt[2].toString(), encrypt[1].toString(), timestamp, "text", sswd(encrypt));
		}else if("3".equals(userMsg)){
			return wxbiz.textRe(encrypt[2].toString(), encrypt[1].toString(), timestamp, "text", elsb(encrypt));
		}
		return wxbiz.textRe(encrypt[2].toString(), encrypt[1].toString(), timestamp, "text", "收到您的留言，请允许我在后台默默地看>_<...\r\n回复0可获取菜单\r\n<a href=\"http://qtwxbp.duapp.com/myQt_index/?openId="+encrypt[2].toString()+"\">oo清汀驿站oo</a>");
	}
	
	//新用户关注自动回复消息内容
	private Map<String,String> textImageMap(Object[] encrypt){
		Map<String,String> map = new HashMap<String, String>();
		map.put("ToUserName",encrypt[2].toString());
		map.put("FromUserName",encrypt[1].toString());
		map.put("CreateTime",timestamp);
		map.put("MsgType","news");
		map.put("ArticleCount","1");
		map.put("Title","清汀驿站");
		map.put("Description","一段旋律，一篇文字，共筑心灵的桃花运...");
		map.put("PicUrl","http://bcs.duapp.com/qt-resources/firstImage.jpeg");
		map.put("Url","http://qtwxbp.duapp.com/myQt_index/?openId="+encrypt[2].toString());
		return map;
	}
	
	//获取菜单
	private String menu(){
		String menu = "回复1.欢迎图文\r\n" +
				"回复2.谁是卧底\r\n" +
				"回复3.2048\r\n" +
				"回复文字向我提建议";
		return menu;
	}
	
	//谁是卧底链接
	private String sswd(Object[] encrypt){
		String sswd = "欢迎进入\r<a href=\"http://qtwxbp.duapp.com/myQt_index/sswd?openId="+encrypt[2].toString()+"\">谁是卧底</a>";
		return sswd;
	}
	//2048链接
	private String elsb(Object[] encrypt){
		String sswd = "欢迎进入\r<a href=\"http://qtwxbp.duapp.com/myQt_index/2048Judge?openId="+encrypt[2].toString()+"\">2048</a>";
		return sswd;
	}
}
