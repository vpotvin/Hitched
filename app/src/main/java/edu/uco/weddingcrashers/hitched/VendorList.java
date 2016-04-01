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
        mVendors.add(new Vendor("Band","band+in+","Band"));
        mVendors.add(new Vendor("Beauty & Health","beauty+salon+in+","Beauty Salon"));
        mVendors.add(new Vendor("Catering","restaurant+in+","Restaurant"));
        mVendors.add(new Vendor("Ceremony & Reception Venue","Reception+Venue+in","Reception Venue"));
        mVendors.add(new Vendor("Ceremony Music","music+in+","Music"));
        mVendors.add(new Vendor("Dress & Attire","wedding+dress+in+","Dress"));
        mVendors.add(new Vendor("Event Rentals & Photobooths","photobooth+in+","EventRental"));
        mVendors.add(new Vendor("Favors & Gift","gift+store+in+","Gifts"));
        mVendors.add(new Vendor("Flowers","florist+in+","Florist"));
        mVendors.add(new Vendor("Guest Accommodations","hotel+in+","Hotel"));
        mVendors.add(new Vendor("Invitations","cards+store+in+","Invitation Cards"));
        mVendors.add(new Vendor("Jewelry","jewelers+in+","Jewelers"));
        mVendors.add(new Vendor("Lighting & Decor","lighting+stores+in+","Decoration"));
        mVendors.add(new Vendor("Men's Formal Wear","formal+wear+in+","Formal Wear"));
        mVendors.add(new Vendor("Registry","Wedding+Registry+in+","Registry"));
        mVendors.add(new Vendor("Wedding Planner","Wedding+planner+in","Wedding Planner"));




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

    public void addVendor(Vendor vendor){
        mVendors.add(vendor);
    }


}
