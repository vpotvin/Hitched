package edu.uco.weddingcrashers.hitched;

import android.support.v4.app.Fragment;

/**
 * Created by Tung on 3/31/2016.
 */
public class RingActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new RingFragment();
    }

}
