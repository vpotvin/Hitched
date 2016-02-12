package edu.uco.weddingcrashers.hitched;

import android.support.v4.app.Fragment;

/**
 * Created by PC User on 2/11/2016.
 */
public class VendorsDetailActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return new VendorsDetailFragment();
    }
}
