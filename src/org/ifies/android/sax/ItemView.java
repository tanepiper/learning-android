package org.ifies.android.sax;

import org.ifies.android.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemView extends LinearLayout {

	public ItemView(Context context, Item item) {
		super(context);
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater lf;
		lf = (LayoutInflater) getContext().getSystemService(infService);
		lf.inflate(R.layout.rss_item, this, true);

		setTextView((TextView)findViewById(R.id.rss_item_title));
		Log.i("Examples", item.getTitle());
		getTextView().setText(item.getTitle());
	}

	public void setTextView(TextView textView) {
		m_TextView = textView;
	}

	public TextView getTextView() {
		return m_TextView;
	}

	private TextView m_TextView;
}
