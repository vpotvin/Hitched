package edu.uco.weddingcrashers.hitched;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by PC User on 3/3/2016.
 */
public class MusicListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new MusicListFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.vendordeal, menu);
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
        if (id == R.id.action_main) { //Rehana Added this 2/11/15
            Intent budget = new Intent(MusicListActivity.this, MainActivity.class);
            startActivity(budget);

        }


        return super.onOptionsItemSelected(item);
    }
}
