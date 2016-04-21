package edu.uco.weddingcrashers.hitched;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by PC User on 2/11/2016.
 */
public class PhotographerVDetailActivity extends PhotographerSingleFragmentActivity {
    private static final String EXTRA_VENDOR_ID = "edu.uco.weddingcrashers.hitched.vendor_id";
    public static Intent newIntent(Context packageContext, UUID vendorID){
        Intent intent = new Intent(packageContext,PhotographerVDetailActivity.class);
        intent.putExtra(EXTRA_VENDOR_ID,vendorID);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        UUID vendorID = (UUID) getIntent().getSerializableExtra(EXTRA_VENDOR_ID);
        return PhotographerDetailFragment.newInstance(vendorID);
    }
}
