package com.demo;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

public class RequestHandler {
	/** 网关url地址 */
	private String gateUrl;

	/** 密钥 */
	private String key;

	/** 请求的参数 */
	private SortedMap<String, String> parameters;

	public RequestHandler(SortedMap<String, String> parameters) {
		this.parameters = parameters;
	}

	public RequestHandler(Map<String, String> parameters) {
		if (null == parameters) {
			parameters = new TreeMap<String, String>();
		}
		Iterator<String> it = parameters.keySet().iterator();
		while (it.hasNext()) {
			String k = it.next();
			String v = parameters.get(k);
			this.setParameter(k, v);
		}
	}

	/**
	 * 初始化函数
	 */
	public void init() {
		// nothing to do
	}

	/**
	 * 获取入口地址，不包含参数值
	 */
	public String getGateUrl() {
		return gateUrl;
	}

	/**
	 * 设置入口地址，不包含参数值
	 */
	public void setGateUrl(String gateUrl) {
		this.gateUrl = gateUrl;
	}

	/**
	 * 获取密钥
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 设置密钥
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 获取参数值
	 * 
	 * @param parameter
	 *            参数名称
	 * @return String
	 */
	public String getParameter(String parameter) {
		String s = (String) this.parameters.get(parameter);
		return (null == s) ? "" : s;
	}

	/**
	 * 设置参数值
	 * 
	 * @param parameter
	 *            参数名称
	 * @param parameterValue
	 *            参数值
	 */
	public void setParameter(String parameter, String parameterValue) {
		String v = "";
		if (null != parameterValue) {
			v = parameterValue.trim();
		}
		this.parameters.put(parameter, v);
	}

	/**
	 * 返回所有参数
	 * 
	 * @return SortedMap
	 */
	public SortedMap<String, String> getAllParameters() {
		return this.parameters;
	}
	
	/**
	 * 创建签名SHA1
	 * 
	 * @param signParams
	 * @return
	 * @throws Exception
	 */
	public String createSHA1Sign() {
		StringBuffer sb = new StringBuffer();
		Set<Entry<String, String>> es = getAllParameters().entrySet();
		Iterator<Entry<String, String>> it = es.iterator();
		while(it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append(k + "=" + v + "&");
		}
		String params = sb.substring(0, sb.lastIndexOf("&"));
		String appsign = Sha1Util.getSha1(params);
		return appsign;
	}
}
