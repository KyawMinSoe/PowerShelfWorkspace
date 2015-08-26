package com.viewgroup.powershelf.philips.lms;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;


@SuppressLint("NewApi")
public class MainActivity extends Activity implements android.view.View.OnClickListener{

	
	RelativeLayout rl_1, rl_2;Button btn_logout,btn_air,btn_juicer,btn_garment,btn_male,btn_report,btn_help;
	RelativeLayout.LayoutParams layoutparam;
	
	public static int widthPixels;
	public static int heightPixels;
	public static Display display;
    public static DisplayMetrics metrics; 
    
    //public static String dir_path = Environment.getExternalStorageDirectory()+"/PhilipsLMSData/";
    public static String dir_path = Environment.getDataDirectory().getAbsolutePath().toString()+"/data/com.viewgroup.powershelf.philips.lms/app_PhilipsLMSData/";
	public static String main_dir = "PhilipsLMSData";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		display = getWindowManager().getDefaultDisplay();	
	    
	    metrics = new DisplayMetrics();
	    display.getMetrics(metrics);
	    widthPixels = metrics.widthPixels;
	    heightPixels = metrics.heightPixels;
	    int margin_first, margin;
	    
	    //Toast.makeText(this, metrics.widthPixels+":"+metrics.heightPixels, Toast.LENGTH_LONG).show();
	    
	    if(widthPixels > 600 && heightPixels > 1024 && DisplayMetrics.DENSITY_MEDIUM == metrics.densityDpi)
		{
	    	setContentView(R.layout.activity_main_mdpilarge);
	    	margin_first = (int) Math.round(heightPixels*0.135);
	    	margin = (int) Math.round(heightPixels*0.118);
		}
	    else if(widthPixels == 600 && heightPixels == 1024 && DisplayMetrics.DENSITY_MEDIUM == metrics.densityDpi)
	    {
	    	setContentView(R.layout.activity_main_mdpilarge);
	    	margin_first = (int) Math.round(heightPixels*0.108);
	    	margin = (int) Math.round(heightPixels*0.098);
	    }
	    else
	    {
	    	setContentView(R.layout.activity_main);
	    	margin_first = (int) Math.round(heightPixels*0.115);
	    	margin = (int) Math.round(heightPixels*0.095);
	    }
		
		rl_1 = (RelativeLayout) findViewById(R.id.rl_1);
		rl_2 = (RelativeLayout) findViewById(R.id.rl_2);
		
		layoutparam = (LayoutParams) rl_1.getLayoutParams();
		layoutparam.setMargins(0, margin_first, 0, 0);
		rl_1.setLayoutParams(layoutparam);
		layoutparam = (LayoutParams) rl_2.getLayoutParams();
		layoutparam.setMargins(0, margin, 0, 0);
		rl_2.setLayoutParams(layoutparam);
		
		btn_logout = (Button) findViewById(R.id.btn_logout);
		btn_logout.setOnTouchListener(new PressedStateOnTouchListener(
				btn_logout.getAlpha()));
		
		btn_air = (Button) findViewById(R.id.btn_air);
		btn_air.setOnTouchListener(new PressedStateOnTouchListener(btn_air.getAlpha()));
		btn_air.setOnClickListener(this);
		
		btn_juicer = (Button) findViewById(R.id.btn_juicer);
		btn_juicer.setOnTouchListener(new PressedStateOnTouchListener(btn_juicer.getAlpha()));
		btn_juicer.setOnClickListener(this);
		
		btn_garment = (Button) findViewById(R.id.btn_garment);
		btn_garment.setOnTouchListener(new PressedStateOnTouchListener(btn_garment.getAlpha()));
		btn_garment.setOnClickListener(this);
		
		btn_male =(Button) findViewById(R.id.btn_male);
		btn_male.setOnTouchListener(new PressedStateOnTouchListener(btn_male.getAlpha()));
		btn_male.setOnClickListener(this);
		
		btn_report = (Button) findViewById(R.id.btn_report);
		btn_report.setOnTouchListener(new PressedStateOnTouchListener(btn_report.getAlpha()));
		btn_report.setOnClickListener(this);
		
		btn_help = (Button) findViewById(R.id.btn_help);
		btn_help.setOnTouchListener(new PressedStateOnTouchListener(btn_help.getAlpha()));
		btn_help.setOnClickListener(this);
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == btn_air)
		{						
			new DownloadBookFile().execute("philips_ar.zip");			
		}
		
		if(v == btn_juicer)
		{
			Intent i = new Intent(MainActivity.this, BookFullScreenViewActivity.class);
			String url = "http://powershelf-dev.3viewsolution.com/philips/juicers_blenders.html";
			i.putExtra("link_url", url);
			startActivity(i);
			finish();
			
		}
		
		if(v == btn_garment)
		{
			Intent i = new Intent(MainActivity.this, BookFullScreenViewActivity.class);
			String url = "http://powershelf-dev.3viewsolution.com/philips/garment_care/01/index.html";
			i.putExtra("link_url", url);
			startActivity(i);
			finish();
			
		}
		
		if(v == btn_male)
		{
			Intent i = new Intent(MainActivity.this, BookFullScreenViewActivity.class);
			String url = "http://powershelf-dev.3viewsolution.com/philips/male_grooming/02/index.html";
			i.putExtra("link_url", url);
			startActivity(i);
			finish();
			
		}
		
		if(v == btn_report)
		{
			Intent i = new Intent(MainActivity.this, BookFullScreenViewActivity.class);
			String url = "http://powershelf-dev.3viewsolution.com/philips/surveys/index.html";
			i.putExtra("link_url", url);
			startActivity(i);
			finish();
			
		}
		if(v == btn_help)
		{
			Intent i = new Intent(MainActivity.this, BookFullScreenViewActivity.class);
			String url = "http://powershelf-dev.3viewsolution.com/philips/help/index.html";
			i.putExtra("link_url", url);
			startActivity(i);
			finish();
			
		}
		
	}
	
	private class DownloadBookFile extends AsyncTask<String, Void, Boolean> {

		private ProgressDialog Dialog = new ProgressDialog(MainActivity.this);

		protected void onPreExecute() {
			Dialog.setCancelable(false);
			Dialog.setMessage("Retreving File...");
			Dialog.show();
		}

		@Override
		protected Boolean doInBackground(String... args) {			
			
			
			if(new File(MainActivity.dir_path+"philips_ar").exists())
			{
				return true;
			}
			else
			{
				new Decompress(args[0], MainActivity.dir_path).unzip();
				return true;
			}
			
			
		}

		protected void onProgressUpdate(Integer... progress) {
		}

		protected void onPostExecute(Boolean result) {
			if (result == true) {				
						
				Intent i = new Intent(MainActivity.this, BookFullScreenViewActivity.class);
				String url = "file:///"+dir_path+"philips_ar/airfryer/index.html";
				i.putExtra("link_url", url);
				startActivity(i);
				finish();
				
			}
			Dialog.dismiss();
		}
		
	}
		
	public static boolean deleteDirectory(File directory) {
	    if(directory.exists()){
	        File[] files = directory.listFiles();
	        if(null!=files){
	            for(int i=0; i<files.length; i++) {
	                if(files[i].isDirectory()) {
	                    deleteDirectory(files[i]);
	                }
	                else {
	                    files[i].delete();
	                }
	            }
	        }
	    }
	    return(directory.delete());
	}
	
	
	@SuppressLint("NewApi")
	public class PressedStateOnTouchListener implements OnTouchListener {
		PressedStateOnTouchListener(float alphaNormal) {
			mAlphaNormal = alphaNormal;
		}

		public boolean onTouch(View theView, MotionEvent motionEvent) {
			switch (motionEvent.getAction()) {
			case MotionEvent.ACTION_DOWN:
				theView.setAlpha(mAlphaNormal / 2.0f);
				break;

			case MotionEvent.ACTION_UP:
				theView.setAlpha(mAlphaNormal);
				break;
			}

			// return false because I still want this to bubble off into an
			// onClick
			return false;
		}

		private float mAlphaNormal;
	}
	
	public class Decompress {

		private String _zipFile; 
		private String _location;
		
		public Decompress(String zipFile, String location) { 
			 _zipFile = zipFile; 
			 _location = location; 
			 _dirChecker(_zipFile.substring(0,_zipFile.lastIndexOf('.')));
		  } 
		
		public void unzip() { 
		    try  {
		    	
		      InputStream ins = getAssets().open(_zipFile);		      
		      ZipInputStream zin = new ZipInputStream(ins);
		      ZipEntry ze = null; 
		      while ((ze = zin.getNextEntry()) != null) { 
		        Log.v("Decompress", "Unzipping " + ze.getName()); 
		 
		        if(ze.isDirectory()) { 
		          _dirChecker(ze.getName()); 
		        } else { 
		          FileOutputStream fout = new FileOutputStream(_location + ze.getName());	          
		          
	              BufferedInputStream in = new BufferedInputStream(zin);
	              BufferedOutputStream out = new BufferedOutputStream(fout);
	              
	              byte b[] = new byte[1024];
	                           
	              int n;
	              while ((n = in.read(b,0,1024)) >= 0) {
	                  out.write(b,0,n);
	              }
	              zin.closeEntry();
	              out.close();
		 
		           
		        } 
		         
		      } 
		      zin.close(); 
		    } catch(Exception e) { 
		      Log.e("Decompress", "unzip", e); 
		    } 
		 
		  } 
		
		private void _dirChecker(String dir) { 
		    File f = new File(_location + dir); 
		 
		    if(!f.isDirectory()) { 
		      f.mkdirs(); 
		    } 
		  } 
	}
	
}
