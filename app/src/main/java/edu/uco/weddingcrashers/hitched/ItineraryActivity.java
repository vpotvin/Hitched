package edu.uco.weddingcrashers.hitched;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
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
        //populateData();

        items = new ArrayList<>();

        ParseQuery<ItineraryItem> query = ParseQuery.getQuery(ItineraryItem.class);

        query.findInBackground(new FindCallback<ItineraryItem>() {
            @Override
            public void done(List<ItineraryItem> objects, ParseException e) {
                items = new ArrayList<>(objects);
                if (items.isEmpty()) {
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
            newFragment = ItineraryDialog.newInstance(item.getTitle(), item.getAssigned(),
                    position);
        }

        if(newFragment != null) newFragment.show(ft, "dialog");
    }

    public void updateItinerary(String title, String assigned, int position, double tip, Date time){
        ItineraryItem item;
        final int pos;
        if(position < 0) {
            item = new ItineraryItem();
            pos = items.size() - 1;
        } else {
            item = items.get(position);
            pos = position;
        }
        item.setTitle(title);
        item.setAssigned(assigned);
        item.setTip(tip);
        if(time != null) item.setTime(time);
        item.saveInBackground();

        ParseQuery<GuestListItem> query = ParseQuery.getQuery(GuestListItem.class);
        query.whereEqualTo("wedding_party", true);
        query.whereEqualTo("name", assigned);



        query.findInBackground(new FindCallback<GuestListItem>() {
            @Override
            public void done(List<GuestListItem> objects, ParseException e) {
                if(objects != null && !objects.isEmpty()) {
                    String[] emails = new String[objects.size()];
                    for(int i = 0; i < objects.size(); i++) {
                        emails[i] = objects.get(i).getEmail();
                    }
                    //TODO troubleshoot email
                    sendEmail(pos, emails);
                }
            }
        });

        if(position < 0) items.add(item);

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

    private void sendEmail(int position, String[] emails) {

        ItineraryItem item = items.get(position);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");

        String emailBody = "Hey, " + item.getAssigned() + "! I have assigned you the " +
                "following task on my wedding day: " + item.getTitle() + ". It will need to happen "
                + "at " + simpleDateFormat.format(item.getTime()) + ".";
        double tip = item.getTip();
        if(tip != 0) emailBody = emailBody + "Please tip " +
                NumberFormat.getCurrencyInstance().format(tip);

        Intent i = new Intent();
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, emails);
        i.putExtra(Intent.EXTRA_SUBJECT, "Wedding Assignment");
        i.putExtra(Intent.EXTRA_TEXT, emailBody);

        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ItineraryActivity.this, "There are no email clients installed.",
                    Toast.LENGTH_SHORT).show();
        }

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
