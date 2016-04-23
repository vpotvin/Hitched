package edu.uco.weddingcrashers.hitched;

import android.support.v4.app.Fragment;


public class DressPictures extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new DressPicturesFragment();
    }
}