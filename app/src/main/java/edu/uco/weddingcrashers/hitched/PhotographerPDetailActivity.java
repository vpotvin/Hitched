package edu.uco.weddingcrashers.hitched;

import android.support.v4.app.Fragment;

public class PhotographerPDetailActivity extends PhotographerSingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new PhotographerDetailsFragment();
    }
}
