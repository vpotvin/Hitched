package edu.uco.weddingcrashers.hitched;

import android.support.v4.app.Fragment;

/**
 * Created by PC User on 3/17/2016.
 */
public class VendorDealActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new VendorDealFragment();
    }
}
