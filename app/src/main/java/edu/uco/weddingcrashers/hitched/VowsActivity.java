package edu.uco.weddingcrashers.hitched;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class VowsActivity extends AppCompatActivity {

    private TextView vowsView;
    private EditText vowsEdit;
    private Button submitButton;
    private ArrayList<WeddingVows> theList;
    private String vows;
    private WeddingVows vowsItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vows);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        vowsEdit = (EditText) findViewById(R.id.editVows);
        vowsView = (TextView) findViewById(R.id.viewVows);
        submitButton = (Button) findViewById(R.id.submitVowsButton);
        vowsItem = new WeddingVows();

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        ParseQuery<WeddingVows> query = ParseQuery.getQuery(WeddingVows.class);

        query.findInBackground(new FindCallback<WeddingVows>() {
            @Override
            public void done(List<WeddingVows> objects, ParseException e) {
                theList = new ArrayList<WeddingVows>(objects);
                if(theList.size() > 0)
                {
                    vowsItem = theList.get(0);
                    vows = vowsItem.getVows();
                    vowsView.setText(vows);
                    vowsEdit.setText(vows);
                }
                vowsView.setVisibility(View.VISIBLE);
            }
        });

        vowsView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                vowsEdit.setVisibility(View.VISIBLE);
                vowsView.setVisibility(View.INVISIBLE);
                submitButton.setVisibility(View.VISIBLE);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                vowsEdit.setVisibility(View.INVISIBLE);
                vowsView.setVisibility(View.VISIBLE);
                submitButton.setVisibility(View.INVISIBLE);
                vows = vowsEdit.getText().toString();
                vowsItem.setVows(vows);
                vowsView.setText(vows);
                vowsItem.saveInBackground(new SaveCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                        }
                    }
                });

            }
        });
    }

}
