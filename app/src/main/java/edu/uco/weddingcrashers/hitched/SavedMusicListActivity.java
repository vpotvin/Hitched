package edu.uco.weddingcrashers.hitched;

import android.support.v4.app.Fragment;

/**
 * Created by PC User on 3/13/2016.
 */
public class SavedMusicListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new SavedMusicListFragment();
    }
}
