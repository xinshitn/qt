package com.qt.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class WriterLog {
	public static void writerLog(String msg){
//		String saveUrl = "/Users/VicenTN/Desktop/sysout.log";
		String saveUrl = "/home/bae/log/sysout.log";
        File sysout = new File(saveUrl);
        PrintWriter pw = null;
//        OutputStreamWriter osw = null;
		try {
//			osw = new OutputStreamWriter(new FileOutputStream(sysout, true),"UTF-8");
//			osw.write(new Date() + " : "+ msg+"\r\n");
			if(sysout.exists()){
				FileWriter filename = new FileWriter(sysout, true);
				pw = new PrintWriter(filename);
			} else {
				pw = new PrintWriter(sysout);
			}
			pw.append(new Date() + " : "+ msg+"\r\n");
			pw.flush();
		} catch (FileNotFoundException e1) {
			System.out.println("日志输出路径不存在！");
		} catch (IOException e) {
			System.out.println("日志信息读写异常！");
		} finally {
			if(pw != null){
				pw.flush();
				pw.close();
			}
//			if(osw != null){
//				try {
//					osw.flush();
//					osw.close();
//				} catch (IOException e) {
//					System.out.println("流关闭异常！");
//				}
//			}
		}
	}
	
	public void dellog(){
		String saveUrl = "/home/bae/log/sysout.log";
        File sysout = new File(saveUrl);
        if(sysout.exists()){
        	sysout.delete();
        }
	}
	
}
