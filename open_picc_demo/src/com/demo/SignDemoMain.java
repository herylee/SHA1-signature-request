package com.demo;

import java.util.Date;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;
/**
 * 
 * 数字签名生成的demo
 *
 */
public class SignDemoMain {
	
	public static void main(String[] args) {
		createSHA1();
	}
	
	private static void createSHA1() {
		SortedMap<String, String> parameters = new TreeMap<String, String>();
		RequestHandler handler = new RequestHandler(parameters);
		handler.setParameter("appKey", "合作商的APP_KEY");
		handler.setParameter("appSecret", "合作商的APP_SECRET");
		handler.setParameter("nonStr", getNonceStr());
		handler.setParameter("timestamp", String.valueOf(new Date().getTime()));
		String signature = handler.createSHA1Sign();
		System.out.println(signature);
	}
	
/***********
	
appKey: ff7a5ed759b4418cb12ce31fc19f42e2021,

appSecret: 3810d48e4d7f47d99d52bad99f34c4a1845,

nonStr: 121321231231231221332,

timestamp: 199003141992015
	
*************/
	
	private static String getNonceStr() {
		Random random = new Random();
		return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), "GBK");
	}
	
}
