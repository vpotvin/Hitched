package edu.uco.weddingcrashers.hitched;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Tung Nguyen on 2/4/2016.
 */
public class VendorList {
    private static VendorList sVendorList;
    private List<Vendor> mVendors;

    public static VendorList get(Context context){
        if(sVendorList == null){
            sVendorList = new VendorList(context);
        }
        return sVendorList;
    }

    private VendorList(Context context){
        mVendors = new ArrayList<>();
        for(int i = 0;i<10;i++){
            Vendor vendor = new Vendor();
            vendor.setVendorName("Vendor "+i);
            vendor.setVendorContact("None");
            vendor.setVendorWebsite("None");

        }
    }

    public List<Vendor> getVendors(){
        return mVendors;
    }

    public Vendor getVendor(UUID id){
        for (Vendor vendor:mVendors){
            if(vendor.getVendorID().equals(id)){
                return vendor;
            }
        }
        return null;
    }


}
