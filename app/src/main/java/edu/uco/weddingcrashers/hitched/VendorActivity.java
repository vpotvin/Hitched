package edu.uco.weddingcrashers.hitched;

import android.support.v4.app.Fragment;

public class VendorActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new VendorFragment();
    }


}
