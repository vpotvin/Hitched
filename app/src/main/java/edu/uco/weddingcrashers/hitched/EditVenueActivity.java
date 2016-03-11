package edu.uco.weddingcrashers.hitched;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class EditVenueActivity extends Activity {

    private Button saveVenue;
    private EditText venueName;
    private String name;
    //private String n1;
    ParseObject venueTable = new ParseObject("Bill_venues");
    private String someList = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_venue);

        saveVenue = (Button) findViewById(R.id.saveVenueButton);
        venueName = (EditText) findViewById(R.id.venueEditText);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            final String n1 = extras.getString("vname");
            String n2 = extras.getString("myvalue");

            if(n2.equals("yes")){

                 venueName.setText(n1);

                saveVenue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        /*ParseQuery<ParseObject> query = ParseQuery.getQuery("Bill_venues");
                        query.whereEqualTo("Name", n1);
                        query.getFirstInBackground(new GetCallback<ParseObject>() {
                            public void done(ParseObject object, ParseException e) {
                                if (object == null) {

                                    Log.d("WHAT", "The getFirst request failed.");

                                } else {

                                    final String objectID = object.getObjectId();
                                    Log.d("WHAT", "Error: " + objectID);

                                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Bill_venues");
                                    query.getInBackground(objectID, new GetCallback<ParseObject>() {
                                        public void done(ParseObject newVenue, ParseException e) {
                                            if (e == null) {
                                                String myNewVenue = venueName.getText().toString();
                                                newVenue.put("Name", myNewVenue);
                                                newVenue.saveInBackground();
                                            } else {
                                                Log.d("WHAT", "Error: " + e.getMessage());
                                            }
                                        }
                                    });
                                }
                            }
                        });*/

                        Intent editVenue = new Intent(EditVenueActivity.this, VenueActivity.class);
                        startActivity(editVenue);
                    }
                });


            } else {

                venueName.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        if (venueName.getText().toString().equals("Enter Venue Name")) {
                            venueName.setText("");
                        }
                    }
                });

                saveVenue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        name = venueName.getText().toString();

                        venueTable.put("Name", name);
                        venueTable.saveInBackground();

                        Intent editVenue = new Intent(EditVenueActivity.this, VenueActivity.class);
                        startActivity(editVenue);
                    }
                });

            }

        }





    }
}
