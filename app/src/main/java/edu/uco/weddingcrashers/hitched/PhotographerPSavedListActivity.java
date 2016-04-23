package edu.uco.weddingcrashers.hitched;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by PC User on 2/19/2016.
 */
public class PhotographerPSavedListActivity extends PhotographerSingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new PhotographerSavedListFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // if(PhotographerSavedList.get(this).getSavedVendors() == null)
             PhotographerSavedList.get(this).setFavoriteList();
    }


}
