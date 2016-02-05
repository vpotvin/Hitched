package edu.uco.weddingcrashers.hitched;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button venue,dress,cake,honeymoon,party,vendor;
    private Button invites,registry,budget,assignseats;
    private Button itinerary,guestlist,tasks,contacts,update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dress = (Button)findViewById(R.id.dress);
        cake = (Button)findViewById(R.id.cake);
        honeymoon = (Button)findViewById(R.id.honeymoon);
        party = (Button)findViewById(R.id.party);
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
         Intent i = new Intent(this,DetailActivity.class);
         startActivity(i);
     }
}
