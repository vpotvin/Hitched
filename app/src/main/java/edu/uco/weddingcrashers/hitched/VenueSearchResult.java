package edu.uco.weddingcrashers.hitched;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;



public class VenueSearchResult extends AppCompatActivity {

    private ListView searchList;
    List<String> arrayList = new ArrayList<String>();
    private String whichList;
    private String thevalue;
   // private static String url = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=wedding+venues+okc&key=AIzaSyBdaXki8OZJsX79vweMiJMaH09bYjXvjWY";

    //private static final String TAG_ADDRESS = "formatted_address";
    //private static final String TAG_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_search_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchList = (ListView) findViewById(R.id.searchListView);

        arrayList.add("Coles Garden" + System.getProperty("line.separator") + System.getProperty("line.separator") + "1415 NE 63rd St, Oklahoma City, OK 73111, United States");
        arrayList.add("Castle Falls" + System.getProperty("line.separator") + System.getProperty("line.separator") + "820 N MacArthur Blvd, Oklahoma City, OK 73127, United States");
        arrayList.add("Sheraton Oklahoma City Downtown Hotel" + System.getProperty("line.separator") + System.getProperty("line.separator") + "1 N Broadway Ave, Oklahoma City, OK 73102, United States");
        arrayList.add("Rose Briar Place" + System.getProperty("line.separator") + System.getProperty("line.separator") + "11900 N Council Rd, Oklahoma City, OK 73102, United States");
        arrayList.add("Ashland Gardens Wedding Chapel" + System.getProperty("line.separator") + System.getProperty("line.separator") + "3633 SE 15th St, Oklahoma City, OK 73115, United States");
        arrayList.add("Harn Homestead" + System.getProperty("line.separator") + System.getProperty("line.separator") + "1721 N Lincoln Blvd, Oklahoma City, OK 73105, United States");
        arrayList.add("NOAH's Event Venue" + System.getProperty("line.separator") + System.getProperty("line.separator") + "14017 Quail Springs Pkwy, Oklahoma City, OK 73134, United States");
        arrayList.add("The Red Barn On Waldos Pond LLC" + System.getProperty("line.separator") + System.getProperty("line.separator") + "1516 E Britton Rd, Oklahoma City, OK 73131, United States");
        arrayList.add("Old Trinity of Paseo" + System.getProperty("line.separator") + System.getProperty("line.separator") + "3000 N Lee Ave, Oklahoma City, OK 73103, United States");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                arrayList );

        searchList.setAdapter(arrayAdapter);

        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent editVenue = new Intent(VenueSearchResult.this, VenueActivity.class);
//
                whichList = searchList.getItemAtPosition(position).toString();
                Log.i("WHAT", whichList);

                thevalue = "yes";
                editVenue.putExtra("vname", whichList);
                editVenue.putExtra("myvalue", thevalue);
                startActivity(editVenue);
            }
        });

    }
}


