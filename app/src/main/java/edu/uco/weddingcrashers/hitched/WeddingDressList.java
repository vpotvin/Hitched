package edu.uco.weddingcrashers.hitched;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class WeddingDressList extends Activity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedding_dress_list);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dressSearch = new Intent(WeddingDressList.this, WeddingDressActivity.class);
                startActivity(dressSearch);
                finish();
            }
        });


        share = (Button) findViewById(R.id.dShareButton);
        dresses = (ListView) findViewById(R.id.dlistView);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareActivity = new Intent(WeddingDressList.this, ShareActivity.class);
                startActivity(shareActivity);
            }
        });

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        dresses.setAdapter(adapter);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            final String n1 = extras.getString("vname");
            String n2 = extras.getString("myvalue");

            if (n2.equals("yes")) {
                arrayList.add(n1);
                adapter.notifyDataSetChanged();
            }
        }

    }
}
