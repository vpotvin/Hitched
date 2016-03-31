package edu.uco.weddingcrashers.hitched;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class VenueDetail extends Activity {

   /* private ProgressDialog pDialog;

    private static String url = "https://maps.googleapis.com/maps/api/place/details/json?placeid=";
    private static final String TAG_RESULT = "result";
    private static final String TAG_NAME = "name";
    private static final String TAG_ADDRESS = "formatted_address";
    private static final String TAG_PHONE = "formatted_phone_number";
    private static final String TAG_WEBSITE = "website";

    JSONArray details = null;

    ArrayList<HashMap<String, String>> theDetails;
    private String whichList;
    private String thevalue;*/

    private String TAG_NAME;
    private String TAG_ADDRESS;
    private String TAG_PHONE;
    private String TAG_WEBSITE;
    ArrayList<HashMap<String, String>> theDetails;
    private TextView name;
    private TextView address;
    private TextView phone;
    private TextView web;
    private Button add;

    private String whichList;
    private String thevalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_detail);

        name = (TextView) findViewById(R.id.name);
        address = (TextView) findViewById(R.id.address);
        phone = (TextView) findViewById(R.id.phone);
        web = (TextView) findViewById(R.id.web);
        add = (Button) findViewById(R.id.addButton);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            final String n3 = extras.getString("name");
            final String n4 = extras.getString("address");

            TAG_NAME = n3;
            TAG_ADDRESS = n4;

            if (TAG_NAME.equals("Coles Garden")) {

                TAG_PHONE = "(405) 478-1529";
                TAG_WEBSITE = "http://www.colesgarden.net/";

                name.setText(TAG_NAME);
                address.setText(TAG_ADDRESS);
                phone.setText(TAG_PHONE);
                web.setText(TAG_WEBSITE);

                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent addVenue = new Intent(VenueDetail.this, VenueActivity.class);

                        String myName = name.getText().toString();
                        String myAddress = address.getText().toString();
                        String myPhone = phone.getText().toString();
                        String myWeb = web.getText().toString();

                        whichList = myName + System.getProperty("line.separator") + System.getProperty("line.separator") +
                                myAddress + System.getProperty("line.separator") + System.getProperty("line.separator") + myPhone
                                + System.getProperty("line.separator") + System.getProperty("line.separator") + myWeb;
                        Log.i("WHAT", whichList);

                        thevalue = "yes";
                        addVenue.putExtra("vname", whichList);
                        addVenue.putExtra("myvalue", thevalue);
                        startActivity(addVenue);
                        finish();
                    }
                });

            } else {

                TAG_PHONE = "(405) 603-7673";
                TAG_WEBSITE = "http://www.rosebriarplace.com/";

                name.setText(TAG_NAME);
                address.setText(TAG_ADDRESS);
                phone.setText(TAG_PHONE);
                web.setText(TAG_WEBSITE);

                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent addVenue = new Intent(VenueDetail.this, WeddingDressList.class);

                        String myName = name.getText().toString();
                        String myAddress = address.getText().toString();
                        String myPhone = phone.getText().toString();
                        String myWeb = web.getText().toString();

                        whichList = myName + System.getProperty("line.separator") + System.getProperty("line.separator") +
                                myAddress + System.getProperty("line.separator") + System.getProperty("line.separator") + myPhone
                                + System.getProperty("line.separator") + System.getProperty("line.separator") + myWeb;
                        Log.i("WHAT", whichList);

                        thevalue = "yes";
                        addVenue.putExtra("vname", whichList);
                        addVenue.putExtra("myvalue", thevalue);
                        startActivity(addVenue);
                        finish();
                    }
                });
            }


            //url = String.valueOf(Uri.parse(url + n1 + "&key=AIzaSyB4cW0S6qfFUERb8he2jOupGO5z9cLiDm4"));
            //Log.i("WHAT", url);
        }


    }



        /*final ListView searchList = getListView();

        searchList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                String name = ((TextView) view.findViewById(R.id.name))
                        .getText().toString();
                String address = ((TextView) view.findViewById(R.id.address))
                        .getText().toString();

                Intent editVenue = new Intent(VenueDetail.this, WeddingDressList.class);

                whichList = name + System.getProperty("line.separator") + System.getProperty("line.separator") + address;
                Log.i("WHAT", whichList);

                thevalue = "yes";
                editVenue.putExtra("vname", whichList);
                editVenue.putExtra("myvalue", thevalue);
                startActivity(editVenue);
                return true;
            }
        });


        new GetDetails().execute();*/

}

    /*private class GetDetails extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing prog
            // ress dialog
            pDialog = new ProgressDialog(VenueDetail.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    details = jsonObj.getJSONArray(TAG_RESULT);

                    // looping through All Contacts
                    for (int i = 0; i < details.length(); i++) {
                        JSONObject c = details.getJSONObject(i);

                        String name = c.getString(TAG_NAME);
                        String address = c.getString(TAG_ADDRESS);
                        String phoneNo = c.getString(TAG_PHONE);
                        String website = c.getString(TAG_WEBSITE);

                        // tmp hashmap for single contact
                        HashMap<String, String> myDetails = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        myDetails.put(TAG_NAME, name);
                        myDetails.put(TAG_ADDRESS, address);
                        myDetails.put(TAG_PHONE, phoneNo);
                        myDetails.put(TAG_WEBSITE, website);
                        // adding contact to contact list
                        theDetails.add(myDetails);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            ListAdapter adapter = new SimpleAdapter(
                    VenueDetail.this, theDetails,
                    R.layout.list_item_detail, new String[] {TAG_NAME, TAG_ADDRESS, TAG_PHONE, TAG_WEBSITE},
                    new int[] {R.id.name,
                            R.id.address,
                            R.id.phoneno,
                            R.id.website});

            setListAdapter(adapter);
        }
    }*/



