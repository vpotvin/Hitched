package edu.uco.weddingcrashers.hitched;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VenueSearch extends AppCompatActivity {

    private Button vSearch;
    private TextView shareText;
    private String search;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_search);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        vSearch = (Button) findViewById(R.id.vSearchButton);
        shareText = (TextView) findViewById(R.id.vSearchText);


        shareText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (shareText.getText().toString().equals("Search for the Perfect Venue")) {
                    shareText.setText("");
                }
            }
        });

        vSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchVenue = new Intent(VenueSearch.this, VenueSearchResult.class);
                search = shareText.getText().toString();
                search = search.replace(" ", "");
                searchVenue.putExtra("search", search);
                startActivity(searchVenue);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.venuesearch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_venuesearch) {
            Intent main = new Intent(VenueSearch.this, MainActivity.class);
            startActivity(main);

        }

        return super.onOptionsItemSelected(item);
    }

}
