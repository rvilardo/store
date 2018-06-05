package com.rvilardo.store.bean;

import org.springframework.http.HttpHeaders;

public class AssetResponse {

	
	private int statusCode;
	private String content;
	
	private boolean isBase64Encoded;
	private HttpHeaders headers;
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public boolean getIsBase64Encoded() {
		return isBase64Encoded;
	}
	public void setIsBase64Encoded(boolean isBase64Encoded) {
		this.isBase64Encoded = isBase64Encoded;
	}
	public HttpHeaders getHeaders() {
		return headers;
	}
	public void setHeaders(HttpHeaders headers) {
		this.headers = headers;
	}
	
	

}
