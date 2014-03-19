package com.iwakfu.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.iwakfu.R;

public class DetailsActivity  extends Activity {

	private WebView webView;
	private String html;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_news_details);
		int id = getIntent().getIntExtra("id",0);
		webView = (WebView)findViewById(R.id.news_details_webview);
		webView.setWebViewClient(new MyWebViewClient());

		webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		webView.getSettings().setJavaScriptEnabled(true);
//		webView.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
		webView.getSettings().setBlockNetworkImage(true);
		webView.loadUrl("http://www.baidu.com/");
	}

	

	/*
	 * ¼àÌý·µ»Ø°´Å¥
	 */
	public void back(View view){
		finish();
	}
	final class MyWebViewClient extends WebViewClient{ 
		public boolean shouldOverrideUrlLoading(WebView view, String url) {  
			return false;  
		} 
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			Log.d("WebView","onPageStarted");
			super.onPageStarted(view, url, favicon);
		}   
		public void onPageFinished(WebView view, String url) {

			super.onPageFinished(view, url);
		}
	}

}