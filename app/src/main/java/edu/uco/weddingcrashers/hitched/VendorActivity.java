package edu.uco.weddingcrashers.hitched;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class VendorActivity extends SingleFragmentActivity{
    private static final String EXTRA_PLACE_ID = "edu.uco.weddingcrashers.hitched.placeID";
    private static final String EXTRA_ICON_URL = "edu.uco.weddingcrashers.hitched.iconURL";
    public static Intent newIntent(Context packageContext, String placeID, String iconURL){
        Intent intent = new Intent(packageContext,VendorActivity.class);
        intent.putExtra(EXTRA_PLACE_ID,placeID);
        intent.putExtra(EXTRA_ICON_URL,iconURL);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        String placeID = (String) getIntent().getSerializableExtra(EXTRA_PLACE_ID);
        String iconURL = (String) getIntent().getSerializableExtra(EXTRA_ICON_URL);
        return VendorFragment.newInstance(placeID,iconURL);
    }


}
