package edu.uco.weddingcrashers.hitched;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class WeddingDressActivity extends Activity {

    private Button dressSearch;
    private EditText mySearch;
    private String search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedding_dress);

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

}
