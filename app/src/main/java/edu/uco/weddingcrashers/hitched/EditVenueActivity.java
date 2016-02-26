package edu.uco.weddingcrashers.hitched;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class EditVenueActivity extends Activity {

    private Button saveVenue;
    private EditText venueName;
    private EditText venueLocation;
    private String name;
    private String location;
    ParseObject venueTable = new ParseObject(ParseDatabase.USER_NAME + "_venues");
    private String someList = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_venue);

        saveVenue = (Button) findViewById(R.id.saveVenueButton);
        venueName = (EditText) findViewById(R.id.venueEditText);
        venueLocation = (EditText) findViewById(R.id.vlocationEditText);


        ArrayList<String> n1 = (ArrayList<String>) getIntent().getSerializableExtra("vname");
        String formattedString1 = n1.toString().replace("[", "").replace("]","").replace(",", "").replace(" ", "");
        venueName.setText(formattedString1);




        venueName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (venueName.getText().toString().equals("Enter Venue Name")) {
                    venueName.setText("");
                }
            }
        });

        venueLocation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (venueLocation.getText().toString().equals("Enter Venue Address")) {
                    venueLocation.setText("");
                }
            }
        });

        saveVenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = venueName.getText().toString();
                location = venueLocation.getText().toString();

                venueTable.put("Name", name);
                venueTable.put("Location", location);
                venueTable.saveInBackground();

                Intent editVenue = new Intent(EditVenueActivity.this, VenueActivity.class);
                editVenue.putExtra("vname", name);
                startActivity(editVenue);
            }
        });
    }
}
