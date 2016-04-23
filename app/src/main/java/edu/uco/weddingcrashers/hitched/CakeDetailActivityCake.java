package edu.uco.weddingcrashers.hitched;

import android.support.v4.app.Fragment;

public class CakeDetailActivityCake extends CakeSingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CakeDetailsFragment();
    }
}
