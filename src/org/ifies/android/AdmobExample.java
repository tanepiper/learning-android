package org.ifies.android;

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
		example_message.setText("This is an example of AdMob for Android");
		
		example_adview = (AdView) findViewById(R.id.ad);
		example_adview.setVisibility(AdView.VISIBLE);
	}
	
	private TextView example_message;
	private AdView example_adview;
}
