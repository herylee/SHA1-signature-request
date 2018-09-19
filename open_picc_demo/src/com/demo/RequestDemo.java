package com.demo;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

/**
 * 
 * 接口调用的demo
 *
 */
public class RequestDemo {
	
	public static void main(String[] args) {
		String requestJSON = "{请求数据的json字符串}";
		String url = "http://{请求地址} ";
		requestURL(url, requestJSON);
	}
	
	private static void requestURL(String url, String requestData) {
		HttpClient httpClient = new HttpClient();
		
		PostMethod post = new PostMethod(url);
		post.addRequestHeader("Content-Type", "application/json");		
		RequestEntity re = new StringRequestEntity(requestData);
		post.setRequestEntity(re);
		
		
		try {
			httpClient.executeMethod(post);
			String responseText = post.getResponseBodyAsString();
			
			System.out.println(responseText);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (post != null) {
				post.releaseConnection();
			}
		}
	}
	
}
