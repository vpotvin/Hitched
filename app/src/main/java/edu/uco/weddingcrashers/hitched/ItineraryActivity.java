package edu.uco.weddingcrashers.hitched;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ItineraryActivity extends Activity {

    ArrayList<ItineraryItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);

        items = new ArrayList<>();


        (findViewById(R.id.testText)).setVisibility(View.VISIBLE);

    }

}
