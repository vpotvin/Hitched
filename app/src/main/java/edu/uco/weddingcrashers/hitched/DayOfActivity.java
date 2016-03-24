package edu.uco.weddingcrashers.hitched;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class DayOfActivity extends AppCompatActivity {
    private Button menu;
    private Button iten;
    private Button seating;
    private Button vows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_of);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        menu = (Button) findViewById(R.id.menuButton);
        iten = (Button) findViewById(R.id.iteneraryButton);
        seating = (Button) findViewById(R.id.seatingButton);
        vows = (Button) findViewById(R.id.vowsButton);

        menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent update = new Intent(DayOfActivity.this, MenuActivity.class);
                startActivity(update);
            }
        });

        iten.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent update = new Intent(DayOfActivity.this, ItineraryActivity.class);
                startActivity(update);
            }
        });

        seating.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent update = new Intent(DayOfActivity.this, FloorPlanActivity.class);
                startActivity(update);
            }
        });

        vows.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent update = new Intent(DayOfActivity.this, WeddingVows.class);
                startActivity(update);
            }
        });

    }

}
