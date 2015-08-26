package com.viewgroup.powershelf.philips.lms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class TitleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.title_view);

		//launch screen thread
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.gc();
				final Intent mainIntent = new Intent(TitleActivity.this,
						MainActivity.class);
				startActivity(mainIntent);
				finish();
			}
		}, 2000);
	}

}
