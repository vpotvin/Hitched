package edu.uco.weddingcrashers.hitched;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WeddingDressActivity extends Activity {

    private Button dressSearch;
    private EditText myDressEditText;
    private String dressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedding_dress);

        dressSearch = (Button) findViewById(R.id.dressSearchButton);
        myDressEditText = (EditText) findViewById(R.id.dressEditText);

        myDressEditText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(myDressEditText.getText().toString().equals("Search for the Perfect Dress")){
                    myDressEditText.setText("");
                }
            }
        });

        dressSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dressText = myDressEditText.getText().toString();
                Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + dressText));
                startActivity(web);
            }
        });
    }

}
