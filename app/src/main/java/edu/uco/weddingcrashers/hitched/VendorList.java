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
        mVendors.add(new Vendor("Band"));
        mVendors.add(new Vendor("Beauty & Health"));
        mVendors.add(new Vendor("Catering"));
        mVendors.add(new Vendor("Ceremony & Reception Venue"));
        mVendors.add(new Vendor("Ceremony Music"));
        mVendors.add(new Vendor("DJ"));
        mVendors.add(new Vendor("Dress & Attire"));
        mVendors.add(new Vendor("Event Rentals & Photobooths"));
        mVendors.add(new Vendor("Favors & Gift"));
        mVendors.add(new Vendor("Flowers"));
        mVendors.add(new Vendor("Guest Accommodations"));
        mVendors.add(new Vendor("Invitations"));
        mVendors.add(new Vendor("Jewelry"));
        mVendors.add(new Vendor("Lighting & Decor"));
        mVendors.add(new Vendor("Men's Formal Wear"));
        mVendors.add(new Vendor("Name Change Service"));
        mVendors.add(new Vendor("Officiant"));


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
