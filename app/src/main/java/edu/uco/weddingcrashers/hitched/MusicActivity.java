package edu.uco.weddingcrashers.hitched;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by PC User on 3/13/2016.
 */
public class MusicActivity extends SingleFragmentActivity {
    private static final String EXTRA_MUSIC_NAME = "edu.uco.weddingcrashers.hitched.musicName";
    private static final String EXTRA_ARTIST_NAME = "edu.uco.weddingcrashers.hitched.artistName";
    public static Intent newIntent(Context packageContext, String trackName, String artistName){
        Intent intent = new Intent(packageContext,MusicActivity.class);
        intent.putExtra(EXTRA_MUSIC_NAME,trackName);
        intent.putExtra(EXTRA_ARTIST_NAME,artistName);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        String artist = getIntent().getStringExtra(EXTRA_ARTIST_NAME);
        String track = getIntent().getStringExtra(EXTRA_MUSIC_NAME);
        return MusicFragment.newInstance(track,artist);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.musicactivity, menu);
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
            Intent budget = new Intent(MusicActivity.this, MainActivity.class);
            startActivity(budget);

        }


        return super.onOptionsItemSelected(item);
    }
}
