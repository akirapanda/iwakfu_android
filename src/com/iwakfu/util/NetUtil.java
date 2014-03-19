package com.iwakfu.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.iwakfu.global.MyConstant;

/**
 * 网络操作工具类
 * 
 * @author lp
 * 
 */
public class NetUtil {
	private static String SESSIONID = null;

	public static String postAndGetDaet(String url) {
		String response = null;
		System.out.println(url);
		try {

			HttpParams parms = new BasicHttpParams();
			parms.setParameter("charset", HTTP.UTF_8);

			HttpConnectionParams.setConnectionTimeout(parms, 8 * 1000);
			HttpConnectionParams.setSoTimeout(parms, 8 * 1000);

			HttpPost httpPost = new HttpPost(url);

			httpPost.setHeader("Cookie", "JSESSIONID=" + SESSIONID);
			httpPost.setHeader("User-Agent", MyConstant.UESRAGENT_PHONE);
			httpPost.setHeader("Accept-Encoding", "UTF-8");
			httpPost.addHeader("Content-Type", "application/json");

			DefaultHttpClient httpClient = new DefaultHttpClient(parms);
			httpClient.getParams().setParameter(
					"http.protocol.content-charset", "UTF-8");
			HttpResponse httpResponse = httpClient.execute(httpPost);

			response = EntityUtils.toString(httpResponse.getEntity(), HTTP.UTF_8);
		} catch (Exception e) {
			System.out.println("error ");
			response = "connect_error";
			e.printStackTrace();
		}
		return response;
	}

}
