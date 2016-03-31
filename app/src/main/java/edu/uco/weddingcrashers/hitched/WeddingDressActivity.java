package edu.uco.weddingcrashers.hitched;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WeddingDressActivity extends AppCompatActivity {

    private Button dressSearch;
    private EditText mySearch;
    private String search;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedding_dress);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dressSearch = (Button) findViewById(R.id.dressSearchButton);
        mySearch = (EditText) findViewById(R.id.dressSearchText);

        mySearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (mySearch.getText().toString().equals("Search for the Perfect Dress")) {
                    mySearch.setText("");
                }
            }
        });

        dressSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchVenue = new Intent(WeddingDressActivity.this, DressSearchResult.class);
                search = mySearch.getText().toString();
                search = search.replace(" ", "");
                searchVenue.putExtra("search", search);
                startActivity(searchVenue);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.weddingdressactivity, menu);
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
        if (id == R.id.action_weddingdressactivity) {
            Intent shareActivity = new Intent(WeddingDressActivity.this, MainActivity.class);
            startActivity(shareActivity);

        }
        return super.onOptionsItemSelected(item);
    }
}
