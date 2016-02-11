package edu.uco.weddingcrashers.hitched;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VenueActivity extends Activity {

    private Button venueSearchButton;
    private EditText venueSearch;
    private String theVenue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue);

        venueSearch = (EditText) findViewById(R.id.venuLocation);
        venueSearchButton = (Button) findViewById(R.id.venueSearchButton);

        venueSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (venueSearch.getText().toString().equals("Enter Venue, City, or State")) {
                    venueSearch.setText("");
                }
            }
        });

        venueSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theVenue = venueSearch.getText().toString();

                if (!theVenue.equals("")) {
                    Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse("https://thehitch.com/wedding-venues?query=" + theVenue));
                    startActivity(web);
                } else {
                    Toast.makeText(getBaseContext(), "Enter Venue Information",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
