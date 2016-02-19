package edu.uco.weddingcrashers.hitched;

import android.support.v4.app.Fragment;

/**
 * Created by PC User on 2/19/2016.
 */
public class SavedVendorListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new SavedVendorListFragment();
    }
}
