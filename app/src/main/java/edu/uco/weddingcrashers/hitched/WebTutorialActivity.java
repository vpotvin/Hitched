package edu.uco.weddingcrashers.hitched;

import android.support.v4.app.Fragment;

/**
 * Created by PC User on 3/18/2016.
 */
public class WebTutorialActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new WebTutorialFragment();
    }
}
