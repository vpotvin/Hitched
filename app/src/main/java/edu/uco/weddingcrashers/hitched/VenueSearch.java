package edu.uco.weddingcrashers.hitched;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class VenueSearch extends AppCompatActivity {

    private Button vSearch;
    private TextView shareText;
    private String search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                //finish();
            }
        });
    }
}
