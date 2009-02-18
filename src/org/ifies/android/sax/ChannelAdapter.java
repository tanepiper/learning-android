package org.ifies.android.sax;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ChannelAdapter extends BaseAdapter {

	public ChannelAdapter(Context context, List<Item> items) {
		setContext(context);
		setItems(items);
	}

	@Override
	public int getCount() {
		return getItems().size();
	}

	@Override
	public Object getItem(int position) {
		return getItems().get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ItemView m_View = null;
		Item item = (Item) getItem(position);
		
		if (convertView == null) {
			m_View = new ItemView(getContext(), item);
		} else {
			m_View = (ItemView) convertView;
			m_View.getTextView().setText(item.getTitle());
		}
		
		return m_View;
	}
	
	public void setContext(Context context) {
		m_Context = context;
	}

	public Context getContext() {
		return m_Context;
	}

	public void setItems(List<Item> items) {
		m_Items = items;
	}

	public List<Item> getItems() {
		return m_Items;
	}

	private Context m_Context;
	private List<Item> m_Items;

}
