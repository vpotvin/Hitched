package edu.uco.weddingcrashers.hitched;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.parse.ParseAnalytics;
import com.parse.ParseUser;

//This activity was created by Rehana Jahan
// Last Edited 2-19-16 12:30AM
public class MainActivity extends Activity {
    private static final String USER_STATE = "UserState";
    private Button venue,dress,party,honeymoon,cake,vendor,picture;
    private Button invites,registry,budget,assignseats;
    private Button itinerary,guestlist,tasks,contacts,update;
    private TextView you, your, date, month, day;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
        you = (TextView)findViewById(R.id.you);
        your = (TextView)findViewById(R.id.your);
        month = (TextView)findViewById(R.id.month);
        day = (TextView)findViewById(R.id.day);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            you.setText(currentUser.getString("bride"));
            your.setText(currentUser.getString("groom"));
            day.setText(currentUser.getString("day"));
            month.setText(currentUser.getString("month"));
        ParseAnalytics.trackAppOpened(getIntent());;

        }else {
            you.setText("");
            your.setText("");
        }
        dress = (Button) findViewById(R.id.dress);
        venue = (Button) findViewById(R.id.venue);
       // party = (Button)findViewById(R.id.party);
        picture = (Button) findViewById(R.id.picture);
        honeymoon = (Button) findViewById(R.id.honeymoon);
        cake = (Button)findViewById(R.id.cake);
        vendor = (Button)findViewById(R.id.vendor);
        invites = (Button)findViewById(R.id.invites);
        registry = (Button)findViewById(R.id.registry);
        budget = (Button)findViewById(R.id.budget);
        assignseats = (Button)findViewById(R.id.assignseats);
        itinerary = (Button)findViewById(R.id.itinerary);
        guestlist = (Button)findViewById(R.id.guestlist);
        tasks = (Button)findViewById(R.id.tasks);
        contacts = (Button)findViewById(R.id.contacts);
        update = (Button)findViewById(R.id.update);


        budget.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent budget = new Intent(MainActivity.this, BudgetActivity.class);
                startActivity(budget);
            }
        });

        dress.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent dress = new Intent(MainActivity.this, WeddingDressActivity.class);
                startActivity(dress);
            }
        });

        venue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent venue = new Intent(MainActivity.this, VenueActivity.class);
                startActivity(venue);
            }
        });


        tasks.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent taskList = new Intent(MainActivity.this, MasterWeddingList.class);
                startActivity(taskList);
            }
        });

        guestlist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent taskList = new Intent(MainActivity.this, GuestListActivity.class);
                startActivity(taskList);
            }
        });

        itinerary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ItineraryActivity.class);
                startActivity(intent);
            }
        });


//rehana
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        return super.onOptionsItemSelected(item);
    }
     public void launchActivity(View view){
         //Tung modified to launch different activity
         Intent i = new Intent(this,DetailActivity.class);
         startActivity(i);

     }

    public void launchBudgetActivity(View view) {
        Intent i = new Intent(this, BudgetActivity.class);
        startActivity(i);
    }

    public void pictureActivity(View view){
        Intent intent = new Intent(MainActivity.this, Pictures.class);
        startActivity(intent);
    }



}
