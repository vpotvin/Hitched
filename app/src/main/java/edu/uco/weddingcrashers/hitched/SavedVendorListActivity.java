package edu.uco.weddingcrashers.hitched;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by PC User on 2/19/2016.
 */
public class SavedVendorListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new SavedVendorListFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // if(SavedVendorList.get(this).getSavedVendors() == null)
             SavedVendorList.get(this).setFavoriteList();
    }


}
