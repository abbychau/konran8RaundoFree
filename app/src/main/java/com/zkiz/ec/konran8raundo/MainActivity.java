package com.zkiz.ec.konran8raundo;

import java.util.concurrent.Callable;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.zkiz.ec.konran8raundo.R;

import android.net.Uri;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.webkit.JsResult;

public class MainActivity extends Activity {

	public WebView wvMyWeb;
	private String strHomepage = "http://ec.zkiz.com/pages/enter.php?worldid=121&app=1";
	public AdView adView;
	public int intPageLoaded = 0;
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		adView = (AdView)this.findViewById(R.id.adView);
	    adView.loadAd(new AdRequest());

		wvMyWeb = (WebView)findViewById(R.id.webView1);
		wvMyWeb.getSettings().setJavaScriptEnabled(true);
		wvMyWeb.getSettings().setDatabaseEnabled(true);
		wvMyWeb.getSettings().setDatabasePath(getString(R.string.dbCacheLocation));
		wvMyWeb.getSettings().setDomStorageEnabled(true);
		wvMyWeb.setWebViewClient(new MyWebViewClient());

		wvMyWeb.setWebChromeClient(new WebChromeClient() {
		    @Override
		    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
		        /* Do whatever you need here */
		    	alertbox("Konran 8 Raundo", message);
		    	return true;
		        //return super.onJsAlert(view, url, message, result); 
		    } 
		});

		wvMyWeb.loadUrl(strHomepage);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
		//WebView wvMyWeb = (WebView)findViewById(R.id.webView1);
		//wvMyWeb.setWebViewClient(new MyWebViewClient());
		
		//
	    switch (item.getItemId()) {
	        case R.id.menu_save:
	        	//alertbox("Setting","Save Clicked");
	        	wvMyWeb.loadUrl("javascript:save1()");
	        	
	            return true;
	        case R.id.menu_load:
	        	wvMyWeb.loadUrl("javascript:load1()");
	        	//wvMyWeb.loadUrl("javascript:$('#form2div').toggle('slow');");
	            return true;
	        case R.id.menu_fb_fan:
	        	//wvMyWeb.loadUrl("http://www.facebook.com/konran8raundo");
	        	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/konran8raundo"));
	        	startActivity(browserIntent);
	        	return true;
	        case R.id.menu_back_to_home:
	        	wvMyWeb.loadUrl(strHomepage);
	        	return true;
	        case R.id.menu_about_ec:
	        	
	            AlertDialog.Builder builder = new AlertDialog.Builder(this);
	            
	            builder.setMessage("Endless Choice is a tool for you to build text adventures in any form you like. \n\nDo you want to visit Endless Choice now?\n\n(this will open a browser window)");
	            builder.setTitle("About Endless Choice");
	            builder.setCancelable(false);
	            
	            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	            	public void onClick(DialogInterface dialog, int id) {
	    	        	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ec.zkiz.com"));
	    	        	startActivity(browserIntent);
	            	}
	            });
	            
	            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
	            	public void onClick(DialogInterface dialog, int id) {
	            		
	            	}
	            });   
	            
	            AlertDialog alert = builder.create();
	            alert.show();
	        	return true;
	        case R.id.menu_rate:
	        	//Intent browserIntent = ;
	        	startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.zkiz.ec.konran8raundo")));
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) 
	   {
	        if ((keyCode == KeyEvent.KEYCODE_BACK)){
	        	
	        	
     		   Intent intent = new Intent(Intent.ACTION_MAIN);
     		   intent.addCategory(Intent.CATEGORY_HOME);
     		   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     		   startActivity(intent);
	        	
	            return false; //I have tried here true also
	        }
	        return super.onKeyDown(keyCode, event);
	   }
	
	protected void alertbox(String title, String mymessage)
	   {
	   new AlertDialog.Builder(this)
	      .setMessage(mymessage)
	      .setTitle(title)
	      .setCancelable(true)
	      .setNeutralButton(android.R.string.ok,
	         new DialogInterface.OnClickListener() {
	         public void onClick(DialogInterface dialog, int whichButton){}
	         })
	      .show();
	   }
	
	protected void yesNoAlertBox(String title, String mymessage,Callable<?> yesListener,Callable<?> noListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        
        builder.setMessage(mymessage);
        builder.setTitle(title);
        builder.setCancelable(false);
        
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int id) {
        		
        	}
        });
        
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int id) {
        	}
        });   
        
        AlertDialog alert = builder.create();
        alert.show();
	}
	
	private class MyWebViewClient extends WebViewClient {
		
		
	   @Override
	   public void onPageFinished(WebView view, String url) {
		   
		   /*
		   intPageLoaded++;
		   
		   if(intPageLoaded % 3 == 1){
			   adView.loadAd(new AdRequest());
		   }
		   */
		   super.onPageFinished(view, url);
	    }
		
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, final String url) {
	        if (Uri.parse(url).getHost().equals("ec.app.zkiz.com")) {
	            // This is my web site, so do not override; let my WebView load the page
	    		//view.loadUrl(url);
	            return false;
	        }

	        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
	        
	        builder.setMessage("This will jump out from Endless Choice, Are you sure?");
	        builder.setTitle("Confirm");
	        builder.setCancelable(false);
	        
	        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface dialog, int id) {
	    	        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
	    	        startActivity(intent);
	        	}
	        });
	        
	        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface dialog, int id) {
	        	}
	        });   
	        
	        AlertDialog alert = builder.create();
	        alert.show();

	        return true;
	    }
	    
	    @Override
	    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
	       // Do something
	 	   new AlertDialog.Builder(MainActivity.this)
		      .setMessage("Lost Connection! Press Menu > 'Back to Home Screen' to retry")
		      .setTitle("Error")
		      .setCancelable(true)
		      .setNeutralButton(android.R.string.ok,
		         new DialogInterface.OnClickListener() {
		         public void onClick(DialogInterface dialog, int whichButton){}
		         })
		      .show();
	    	view.loadUrl("file:///android_asset/missing.html");
	    	view.setBackgroundColor(Color.GRAY);
	    }
	}
}

