package edu.uco.weddingcrashers.hitched;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class DressDetail extends ListActivity {


    private ProgressDialog pDialog;

    private static String url = "https://maps.googleapis.com/maps/api/place/details/json?placeid=";
    private static final String TAG_RESULTS = "result";
    private static final String TAG_NAME = "name";
    private static final String TAG_ADDRESS = "formatted_address";
    private static final String TAG_PHONE = "formatted_phone_number";

    JSONObject venues = null;

    ArrayList<HashMap<String, String>> venueList;
    private String whichList;
    private String thevalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dress_detail);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            final String n1 = extras.getString("place_id");
            url = String.valueOf(Uri.parse(url + n1 + "&key=AIzaSyAOqYf1wSCRpbk9EaSUy4B3MN8S7F6uiNc"));
            Log.i("WHAT:", url);
        }

        venueList = new ArrayList<HashMap<String, String>>();

        final ListView searchList = getListView();

        searchList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                String name = ((TextView) view.findViewById(R.id.name))
                        .getText().toString();

                Intent editVenue = new Intent(DressDetail.this, WeddingDressList.class);

                whichList = name + System.getProperty("line.separator");
                Log.i("WHAT", whichList);

                thevalue = "yes";
                editVenue.putExtra("vname", whichList);
                editVenue.putExtra("myvalue", thevalue);
                startActivity(editVenue);
                finish();
                return true;
            }
        });


        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent pictureVenue = new Intent(DressDetail.this, DressPictures.class);


                startActivity(pictureVenue);
            }
        });

        new GetVenues().execute();

    }

    private class GetVenues extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing prog
            // ress dialog
            pDialog = new ProgressDialog(DressDetail.this);
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
                    venues = jsonObj.getJSONObject(TAG_RESULTS);
                    String address = venues.getString(TAG_ADDRESS);
                    String name = venues.getString(TAG_NAME);
                    String phone = venues.getString(TAG_PHONE);

                    HashMap<String, String> myVenues = new HashMap<String, String>();

                    myVenues.put(TAG_NAME, name);
                    myVenues.put(TAG_ADDRESS, address);
                    myVenues.put(TAG_PHONE, phone);
                    venueList.add(myVenues);


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
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    DressDetail.this, venueList,
                    R.layout.list_item_detail, new String[] {TAG_NAME, TAG_ADDRESS, TAG_PHONE},
                    new int[] {R.id.name,
                            R.id.address,
                            R.id.phoneno});

            setListAdapter(adapter);

        }
    }
}