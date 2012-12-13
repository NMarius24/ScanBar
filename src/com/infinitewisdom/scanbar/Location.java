package com.infinitewisdom.scanbar;


import android.os.Bundle;

import com.google.android.maps.MapActivity;

public class Location extends MapActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
