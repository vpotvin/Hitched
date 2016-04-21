package edu.uco.weddingcrashers.hitched;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by PC User on 2/19/2016.
 */
public class CakeSavedListActivityCake extends CakeSingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CakeSavedListFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // if(CakeSavedList.get(this).getSavedVendors() == null)
             CakeSavedList.get(this).setFavoriteList();
    }


}
