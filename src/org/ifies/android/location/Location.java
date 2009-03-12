package org.ifies.android.location;

import org.ifies.android.R;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class Location extends MapActivity {
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location_example);
		setMapView((MapView)findViewById(R.id.mapview));
		setLocationManager((LocationManager)getSystemService(Context.LOCATION_SERVICE));
		setMapController(getMapView().getController());
		setZoomLayout((LinearLayout)findViewById(R.id.layout_zoom));  
		setZoomView(getMapView().getZoomControls()); 
		
		getZoomLayout().addView(getZoomView(), new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)); 
		getMapView().displayZoomControls(true);
	}
	
	public void setLocationManager(LocationManager locationManager) {
		m_LocationManager = locationManager;
	}

	public LocationManager getLocationManager() {
		return m_LocationManager;
	}

	public void setMapView(MapView mapView) {
		m_MapView = mapView;
	}

	public MapView getMapView() {
		return m_MapView;
	}

	public void setMapController(MapController mapController) {
		m_MapController = mapController;
	}

	public MapController getMapController() {
		return m_MapController;
	}

	public void setZoomLayout(LinearLayout zoomLayout) {
		m_ZoomLayout = zoomLayout;
	}

	public LinearLayout getZoomLayout() {
		return m_ZoomLayout;
	}

	public void setZoomView(View zoomView) {
		m_ZoomView = zoomView;
	}

	public View getZoomView() {
		return m_ZoomView;
	}

	private LocationManager m_LocationManager;
	private MapView m_MapView;
	private MapController m_MapController;
	private LinearLayout m_ZoomLayout;
	private View m_ZoomView;

}
