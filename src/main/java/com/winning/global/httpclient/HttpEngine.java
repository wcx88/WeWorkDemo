package com.winning.global.httpclient;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by wcx on 2017/7/14.
 */
public class HttpEngine {
	// 超时时间
    public static int SERVER_TIMEOUT = 60000;
    
    /**
     * @return
     * @throws Exception
     */
    public static String getRequest(String url) throws HttpException, IOException {
        HttpEngine engine = new HttpEngine();
        return engine.httpGetRequest(url);
    }
    /**
     * @param postContent
     * @return
     * @throws Exception
     */
    public static String postRequest(String url, String postContent) throws HttpException, IOException {
    	HttpEngine engine = new HttpEngine();
    	return engine.httpPostRequest(url, postContent);
    }
    
    /***
     * http get提交
     * @param url
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public String httpGetRequest(String url) throws HttpException, IOException {
    	String responseText = null;
    	
    	HttpClient client = new HttpClient();
        GetMethod httpMethod = new GetMethod(url);
    	try {
    		httpMethod.setRequestHeader("Content-Type", "application/json");
    		httpMethod.getParams().setSoTimeout(SERVER_TIMEOUT);
    		
    		int status = client.executeMethod(httpMethod);

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpMethod.getResponseBodyAsStream(), "UTF-8"));
    		StringBuffer sb = new StringBuffer();
    		String str = "";
    		while((str = reader.readLine())!=null){  
    			sb.append(str);  
    		}
    		responseText = sb.toString();
    		
    	} finally {
    		//释放连接
    		if(httpMethod != null) {
    			httpMethod.releaseConnection();
    		}
    	}
    	return responseText;
    }
    /***
     * http post提交json
     * @param url
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public String httpPostRequest(String url, String postContent) throws HttpException, IOException {

    	PostMethod httpMethod = new PostMethod(url);
    	String responseText = null;
    	
    	HttpClient client = new HttpClient();
    	try {
    		httpMethod.setRequestHeader("Content-Type", "application/json");
    		httpMethod.getParams().setSoTimeout(SERVER_TIMEOUT);
	    	if(postContent != null) {
	    		RequestEntity entity = new StringRequestEntity(postContent, "application/json", "UTF-8");
	    		httpMethod.setRequestEntity(entity);
	    	}
    		
    		int status = client.executeMethod(httpMethod);
    		
    		BufferedReader reader = new BufferedReader(new InputStreamReader(httpMethod.getResponseBodyAsStream(), "UTF-8"));
    		StringBuffer sb = new StringBuffer();
    		String str = "";
    		while((str = reader.readLine())!=null){  
    			sb.append(str);  
    		}
    		responseText = sb.toString();
    		
    	} finally {
    		//释放连接
    		if(httpMethod != null) {
    			httpMethod.releaseConnection();
    		}
    	}
    	return responseText;
    }
}
