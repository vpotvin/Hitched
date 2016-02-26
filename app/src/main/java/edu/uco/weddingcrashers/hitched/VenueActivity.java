package edu.uco.weddingcrashers.hitched;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.internal.ParcelableSparseArray;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class VenueActivity extends Activity {

    private Button addVenue;
    private Button share;
    private ListView venues;
    private String listType;
    List<String> arrayList = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    private String someList = "";
    private String whichList;
    private final int RETURN = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue);


        share = (Button) findViewById(R.id.vsharebutton);
        venues = (ListView) findViewById(R.id.vListView);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editVenue = new Intent(VenueActivity.this, EditVenueActivity.class);
                startActivity(editVenue);
            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editVenue = new Intent(VenueActivity.this, EditVenueActivity.class);
                startActivity(editVenue);
            }
        });


       adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        venues.setAdapter(adapter);

        if (ParseDatabase.USER_NAME.equals("Bill")) {

            ParseQuery<ParseObject> query = ParseQuery.getQuery(ParseDatabase.USER_NAME + "_venues");
            query.whereExists("Name");
            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> object, ParseException e) {
                    if (e == null) {

                        Log.i("WHAT", object.size() + "  " + someList);

                        for (ParseObject dealsObject : object) {
                            arrayList.add(dealsObject.get("Name").toString());
                            Log.i("WHAT", arrayList.toString());
                        }

                        Log.i("WHAT", arrayList.toString());
                        adapter.notifyDataSetChanged();
                    } else {
                        Log.d("score", "Error: " + e.getMessage());
                    }
                }
            });
        } else if(ParseDatabase.USER_NAME.equals("John")) {

            ParseQuery<ParseObject> query = ParseQuery.getQuery("Bill_venues");
            query.whereExists("Name");
            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> object, ParseException e) {
                    if (e == null) {

                        Log.i("WHAT", object.size() + "  " + someList);

                        for (ParseObject dealsObject : object) {
                            arrayList.add(dealsObject.get("Name").toString());
                            Log.i("WHAT", arrayList.toString());
                        }

                        Log.i("WHAT", arrayList.toString());
                        adapter.notifyDataSetChanged();
                    } else {
                        Log.d("score", "Error: " + e.getMessage());
                    }
                }
            });
        }


        venues.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent editVenue = new Intent(VenueActivity.this, EditVenueActivity.class);
//
                whichList = venues.getItemAtPosition(position).toString();
                Log.i("WHAT", whichList);

                editVenue.putExtra("vname", whichList);
                editVenue.putExtra("value", "true");
                startActivityForResult(editVenue, RETURN);
            }
        });

        venues.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String what = parent.getItemAtPosition(position).toString();

                ParseQuery<ParseObject> query2 = ParseQuery.getQuery(ParseDatabase.USER_NAME + "_venues");
                query2.whereEqualTo("Name", what);
                query2.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> object, ParseException e) {
                        if (e == null) {
                            for (ParseObject objects : object) {
                                objects.deleteInBackground();
                            }
                        } else {
                        }
                    }
                });
                adapter.remove(what);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery(ParseDatabase.USER_NAME + "_venues");
        query.getInBackground("Name", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, com.parse.ParseException e) {
                if (e == null) {
                    someList = object.getString("Name");
                    Log.i("WHAT", object.toString() + "  " + someList);

                    arrayList.add(someList);
                    adapter.notifyDataSetChanged();
                } else {
                    // something went wrong
                }
            }
        });



    }

}
