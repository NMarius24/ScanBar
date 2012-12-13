package com.infinitewisdom.scanbar;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



import android.os.Bundle;

import com.google.android.maps.MapActivity;

public class Main extends MapActivity {
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


/*
public class Main extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final Button button = (Button) findViewById(R.id.scan_it);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            }
        });
    }


}
*/
