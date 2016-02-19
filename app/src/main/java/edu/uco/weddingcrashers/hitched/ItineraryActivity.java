package edu.uco.weddingcrashers.hitched;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ItineraryActivity extends Activity implements ItineraryDialog.ItineraryUpdateListener {

    ArrayList<ItineraryItem> items;
    ListView itineraryList;
    ArrayAdapter itineraryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);

        items = new ArrayList<>();

        ParseQuery<ItineraryItem> query = ParseQuery.getQuery(ItineraryItem.class);

        query.findInBackground(new FindCallback<ItineraryItem>() {
            @Override
            public void done(List<ItineraryItem> objects, ParseException e) {
                items = new ArrayList<ItineraryItem>(objects);
                itineraryList = (ListView) findViewById(R.id.itinerary_list);
                itineraryAdapter = new ItineraryItemAdapter(ItineraryActivity.this, items);
                itineraryList.setAdapter(itineraryAdapter);
            }
        });


    }

    public void createDialog(View v) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        DialogFragment newFragment = ItineraryDialog.newInstance("", "", -1);

        newFragment.show(ft, "dialog");
    }

    public void updateItinerary(String title, String assigned, int position, boolean isNew) {
        if(isNew) {
            ItineraryItem item = new ItineraryItem();
            item.setTitle(title);
            item.setAssigned(assigned);
            item.saveInBackground();

            items.add(item);
        }


    }

    private void updateView() {
        itineraryAdapter.notifyDataSetChanged();
    }

}
