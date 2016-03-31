package edu.uco.weddingcrashers.hitched;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class VenueActivity extends AppCompatActivity{

    private Button addVenue;
    private Button share;
    private ListView venues;
    private String listType;
    List<String> arrayList = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    private String someList = "";
    private String whichList;
    private String thevalue;
    private final int RETURN = 1;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        venues = (ListView) findViewById(R.id.vListView);


        ParsePush.subscribeInBackground("Venues");


        Button fab = (Button) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editVenue = new Intent(VenueActivity.this, VenueSearch.class);
                thevalue = "no";
                editVenue.putExtra("myvalue", thevalue);
                startActivity(editVenue);
                //finish();
            }
        });


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        venues.setAdapter(adapter);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            final String n1 = extras.getString("vname");
            String n2 = extras.getString("myvalue");

            if(n2.equals("yes")){
                arrayList.add(n1);
                adapter.notifyDataSetChanged();
            }

            else{
                if (ParseDatabase.USER_NAME.equals("Bill")) {

                    final ParseQuery<ParseObject> query = ParseQuery.getQuery(ParseDatabase.USER_NAME + "_venues");
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
                                Log.d("WHAT", "Error: " + e.getMessage());
                            }
                        }
                    });
                } else if(ParseDatabase.USER_NAME.equals("Sarah")) {

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
                                Log.d("WHAT", "Error: " + e.getMessage());
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

                        thevalue = "yes";
                        editVenue.putExtra("vname", whichList);
                        editVenue.putExtra("myvalue", thevalue);
                        startActivity(editVenue);
                    }
                });

                venues.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        final String what = parent.getItemAtPosition(position).toString();

                        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Bill_venues");
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
        }
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.venueactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_venueshare) {
            Intent shareActivity = new Intent(VenueActivity.this, ShareActivity.class);
            startActivity(shareActivity);

        }
        if (id == R.id.action_main) {
            Intent main = new Intent(VenueActivity.this, MainActivity.class);
            startActivity(main);

        }

        return super.onOptionsItemSelected(item);
    }
}

