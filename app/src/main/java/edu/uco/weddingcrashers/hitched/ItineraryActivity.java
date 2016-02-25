package edu.uco.weddingcrashers.hitched;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ItineraryActivity extends Activity implements ItineraryDialog.ItineraryUpdateListener {

    ArrayList<ItineraryItem> items;
    ListView itineraryList;
    ArrayAdapter itineraryAdapter;

    private static final int UPDATE_TYPE = 1;
    private static final int NEW_TYPE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);

        //Populate test data if needed.
//        populateData();

        items = new ArrayList<>();

        ParseQuery<ItineraryItem> query = ParseQuery.getQuery(ItineraryItem.class);

        query.findInBackground(new FindCallback<ItineraryItem>() {
            @Override
            public void done(List<ItineraryItem> objects, ParseException e) {
                items = new ArrayList<ItineraryItem>(objects);
                if(items.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "There was no data found",
                            Toast.LENGTH_SHORT);
                }
                itineraryList = (ListView) findViewById(R.id.itinerary_list);
                itineraryAdapter = new ItineraryItemAdapter(ItineraryActivity.this, items);
                itineraryList.setAdapter(itineraryAdapter);

                itineraryList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                        createDialog(position, UPDATE_TYPE);
                        return false;
                    }
                });
            }
        });




    }

    public void createDialog(View v) {
        createDialog(-1, NEW_TYPE);
    }

    private void createDialog(int position, int type) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        DialogFragment newFragment = null;

        if(type == NEW_TYPE)
            newFragment = ItineraryDialog.newInstance("", "", -1);
        else if(type == UPDATE_TYPE) {
            ItineraryItem item = items.get(position);
            newFragment = ItineraryDialog.newInstance(item.getTitle(), item.getAssigned(), position);
        }

        newFragment.show(ft, "dialog");
    }

    public void updateItinerary(String title, String assigned, int position) {
        if(position < 0) {
            ItineraryItem item = new ItineraryItem();
            item.setTitle(title);

            //TODO: pull email address for assigned and send automated email;
            item.setAssigned(assigned);
            item.saveInBackground();
            items.add(item);
        } else {
            ItineraryItem item = items.get(position);
            item.setTitle(title);
            item.setAssigned(assigned);
            item.saveInBackground();
        }
        updateView();
        removeDialog();

    }

    public void deleteItineraryItem(int position) {
        ItineraryItem item = items.get(position);
        item.deleteInBackground();
        items.remove(position);

        updateView();
        removeDialog();
    }

    private void removeDialog() {
        Fragment dialog = getFragmentManager().findFragmentByTag("dialog");
        if (dialog != null) ((DialogFragment) dialog).dismiss();
    }

    private void updateView() {
        itineraryAdapter.notifyDataSetChanged();
    }

    //Only used for debugging purposes to repopulate Database table
    private void populateData() {
        ItineraryItem item = new ItineraryItem();
        item.setAssigned("Susan");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2050, 12, 6, 9, 30);
        Date date = calendar.getTime();
        item.setTime(date);
        item.setTitle("Testing Title");
        item.setTip(100);

        item.saveInBackground();

    }

}
