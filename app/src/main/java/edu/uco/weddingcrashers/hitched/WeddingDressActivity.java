package edu.uco.weddingcrashers.hitched;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class WeddingDressActivity extends AppCompatActivity {

    private Button dressSearch;
    private EditText stateText;
    private EditText cityText;
    private String theState;
    private String theCity;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedding_dress);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        dressSearch = (Button) findViewById(R.id.dressSearchButton);
        stateText = (EditText) findViewById(R.id.myStateText);
        cityText = (EditText) findViewById(R.id.myCityText);

        stateText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(stateText.getText().toString().equals("Enter State (Two Letter Abbreviation)")){
                    stateText.setText("");
                }
            }
        });

        cityText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(cityText.getText().toString().equals("Enter City")){
                    cityText.setText("");
                }
            }
        });

        dressSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theCity = cityText.getText().toString();
                theState = stateText.getText().toString();
                if (theState.length() == 2) {
                    Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse("http://locations.alfredangelo.com/" + theState + "/" + theCity + "/"));
                    startActivity(web);
                } else {
                    Toast.makeText(getBaseContext(), "Enter Two Letter State Abbreviation",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
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
          if (id == R.id.action_dress) {

              Intent intent = new Intent(WeddingDressActivity.this, MainActivity.class);
              startActivity(intent);
    }
        return super.onOptionsItemSelected(item);
    }

}
