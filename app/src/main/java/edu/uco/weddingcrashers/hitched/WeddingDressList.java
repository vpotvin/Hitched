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
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class WeddingDressList extends AppCompatActivity {

    private Button addVenue;
    private Button share;
    private ListView dresses;
    private String listType;
    List<String> arrayList = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    private String someList = "";
    private String whichList;
    private String thevalue;
    private final int RETURN = 1;
    private Toolbar toolbar;
    ParseObject dressList = new ParseObject(ParseDatabase.USER_NAME + "_dresses");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedding_dress_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        Button fab = (Button) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dressSearch = new Intent(WeddingDressList.this, WeddingDressActivity.class);
                startActivity(dressSearch);
                finish();
            }
        });



        dresses = (ListView) findViewById(R.id.dlistView);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        dresses.setAdapter(adapter);

        dresses.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String what = parent.getItemAtPosition(position).toString();
                arrayList.remove(what);
                adapter.notifyDataSetChanged();
                dressList.remove(what);
                dressList.saveInBackground();
                return true;
            }
        });

        final ParseQuery<ParseObject> query = ParseQuery.getQuery(ParseDatabase.USER_NAME + "_dresses");
        query.whereExists("Dress");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> object, ParseException e) {
                if (e == null) {

                    Log.i("WHAT", object.size() + "  " + someList);

                    for (ParseObject dealsObject : object) {
                        arrayList.add(dealsObject.get("Dress").toString());
                        Log.i("WHAT", arrayList.toString());
                    }

                    Log.i("WHAT", arrayList.toString());
                    adapter.notifyDataSetChanged();
                } else {
                    Log.d("WHAT", "Error: " + e.getMessage());
                }
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            final String n1 = extras.getString("vname");
            String n2 = extras.getString("myvalue");

            if (n2.equals("yes")) {
                arrayList.add(n1);
                adapter.notifyDataSetChanged();
                dressList.put("Dress", n1);
                dressList.saveInBackground();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.weddingdress, menu);
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
        if (id == R.id.action_weddingshare) {
            Intent shareActivity = new Intent(WeddingDressList.this, ShareActivity.class);
            startActivity(shareActivity);

        }

        if (id == R.id.action_main) {
            Intent shareActivity = new Intent(WeddingDressList.this, MainActivity.class);
            startActivity(shareActivity);

        }
        return super.onOptionsItemSelected(item);
    }








}
