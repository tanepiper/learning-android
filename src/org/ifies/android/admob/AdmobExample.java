package org.ifies.android.admob;

import org.ifies.android.R;

import com.admob.android.ads.AdView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AdmobExample extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.admob_example);
		
		example_message = (TextView) findViewById(R.id.example_message);
		example_message.setText("This is an example of AdMob for Android.\n\n"
				+ "Please wait while an advert is loaded in to see it in action.\n\n"
				+ "The sourcecode for this application is available from http://android.ifies.org");
		
		example_adview = (AdView) findViewById(R.id.ad);
		example_adview.setVisibility(AdView.VISIBLE);
	}
	
	private TextView example_message;
	private AdView example_adview;
}
