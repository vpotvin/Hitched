package edu.uco.weddingcrashers.hitched;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class FloorPlanActivity extends AppCompatActivity {

    private List<Table> tables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_plan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ParseQuery<Table> query = ParseQuery.getQuery(Table.class);
        query.findInBackground(new FindCallback<Table>() {
            @Override
            public void done(List<Table> objects, ParseException e) {
                if(!objects.isEmpty()) tables = objects;
                else tables = new ArrayList<>();
            }
        });
    }


    public void addTable(View v) {
        //TODO create/open dialog

    }

    private void updateView() {
        FrameLayout tableFrame = (FrameLayout) findViewById(R.id.tableFrame);
        tableFrame.removeAllViews();
        int c = 0;
        for(int i = 0; i < tables.size(); i++ ) {
            Button button = new Button(this);
            String title = "Table " + c;
            button.setText(title);
            //TODO add click listener for pulling up dialog fragment
            tableFrame.addView(button);
        }


    }

}
