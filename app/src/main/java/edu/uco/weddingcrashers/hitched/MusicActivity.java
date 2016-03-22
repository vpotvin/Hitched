package edu.uco.weddingcrashers.hitched;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

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
}
