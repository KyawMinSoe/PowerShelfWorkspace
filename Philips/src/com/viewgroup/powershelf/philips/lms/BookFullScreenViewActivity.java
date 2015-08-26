package com.viewgroup.powershelf.philips.lms;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings.PluginState;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;


@SuppressLint("NewApi")
public class BookFullScreenViewActivity extends Activity{ 

	HTML5WebView mWebView;String link_url;RelativeLayout pager_book;RelativeLayout.LayoutParams layoutparam;
	
	public static boolean first_start  = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_view);
		
		pager_book = (RelativeLayout)findViewById(R.id.pager_book);
		
		Intent i = getIntent();
		link_url = i.getStringExtra("link_url");
		
		mWebView = new HTML5WebView(this);
		
		
		mWebView.setInitialScale(1);        
    	mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setSupportZoom(true);
	    mWebView.getSettings().setUseWideViewPort(true);
	    mWebView.getSettings().setLoadWithOverviewMode(true);      
	    mWebView.getSettings().setBuiltInZoomControls(true);
	    mWebView.getSettings().setDisplayZoomControls(false);	        
		mWebView.getSettings().setAllowFileAccess(true);
		mWebView.getSettings().setAppCacheEnabled(true);	
		mWebView.getSettings().setDomStorageEnabled(true);
		mWebView.getSettings().setPluginState(PluginState.OFF);
		mWebView.getSettings().setAllowFileAccess(true);
		mWebView.setVerticalScrollBarEnabled(false);
		mWebView.requestFocus(View.FOCUS_DOWN);
		mWebView.loadUrl(link_url);
		
		layoutparam = (LayoutParams) new RelativeLayout.LayoutParams((int) Math.round(MainActivity.heightPixels*0.82),MainActivity.widthPixels+10);
		layoutparam.addRule(RelativeLayout.CENTER_HORIZONTAL);
		layoutparam.addRule(RelativeLayout.CENTER_VERTICAL);
		pager_book.addView(mWebView.getLayout(),layoutparam);
		
	}
	
		
	@Override
	public void onBackPressed() {
		
		//super.onBackPressed();
		
		//Log.i("Back", "click");
		
		mWebView.reload();
		/*
		if(mWebView.canGoBack() )
		{
			//Toast.makeText(BookFullScreenViewActivity.this, "get here 1", Toast.LENGTH_LONG).show();
			while(mWebView.copyBackForwardList().getCurrentIndex() > 0)
			mWebView.goBack();
		}*/
		
		Intent i = new Intent(BookFullScreenViewActivity.this, MainActivity.class);
		BookFullScreenViewActivity.this.startActivity(i);
		BookFullScreenViewActivity.this.finish();
		
	}
}


