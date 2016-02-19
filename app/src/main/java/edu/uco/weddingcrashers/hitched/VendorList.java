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
        mVendors.add(new Vendor("Band","https://www.google.com/search?q=band+wedding+vendor&oq=band+wedding+vendor&aqs=chrome..69i57.5396j0j4&sourceid=chrome-mobile&ie=UTF-8#q=band+wedding+vendor+in+okc&istate=lrl:xpd"));
        mVendors.add(new Vendor("Beauty & Health","https://www.google.com/search?q=beauty+salonr&oq=beauty+salon&aqs=chrome..69i57.5396j0j4&sourceid=chrome-mobile&ie=UTF-8#q=beauty+salon+in+okc&istate=lrl:xpd"));
        mVendors.add(new Vendor("Catering","https://www.google.com/search?q=catering+wedding+vendor&oq=catering+wedding+vendor&aqs=chrome..69i57.5396j0j4&sourceid=chrome-mobile&ie=UTF-8#q=catering+wedding+vendor+in+okc&istate=lrl:xpd"));
        mVendors.add(new Vendor("Ceremony & Reception Venue","https://www.google.com/search?q=reception+venue&oq=reception+venuer&aqs=chrome..69i57.5396j0j4&sourceid=chrome-mobile&ie=UTF-8#q=reception+venue+in+okc&istate=lrl:xpd"));
        mVendors.add(new Vendor("Ceremony Music","https://www.google.com/search?q=wedding+musicr&oq=wedding+music&aqs=chrome..69i57.5396j0j4&sourceid=chrome-mobile&ie=UTF-8#q=wedding+music+in+okc&istate=lrl:xpd"));
        mVendors.add(new Vendor("Dress & Attire","https://www.google.com/search?q=wedding+dress&oq=wedding+dress&aqs=chrome..69i57.5396j0j4&sourceid=chrome-mobile&ie=UTF-8#q=wedding+dress+in+okc&istate=lrl:xpd"));
        mVendors.add(new Vendor("Event Rentals & Photobooths","https://www.google.com/search?q=event+rental&oq=event+rental&aqs=chrome..69i57.5396j0j4&sourceid=chrome-mobile&ie=UTF-8#q=event+rental+in+okc&istate=lrl:xpd"));
        mVendors.add(new Vendor("Favors & Gift","https://www.google.com/search?q=wedding+gift+store&oq=wedding+gift+store&aqs=chrome..69i57.5396j0j4&sourceid=chrome-mobile&ie=UTF-8#q=wedding+gift+store+in+okc&istate=lrl:xpd"));
        mVendors.add(new Vendor("Flowers","https://www.google.com/search?q=florist&oq=florist&aqs=chrome..69i57.5396j0j4&sourceid=chrome-mobile&ie=UTF-8#q=florist+in+okc&istate=lrl:xpd"));
        mVendors.add(new Vendor("Guest Accommodations","https://www.google.com/search?q=hotel&oq=hotel&aqs=chrome..69i57.5396j0j4&sourceid=chrome-mobile&ie=UTF-8#q=hotel+in+okc&istate=lrl:xpd"));
        mVendors.add(new Vendor("Invitations","https://www.google.com/search?q=cards+stores&oq=cards+stores&aqs=chrome..69i57.5396j0j4&sourceid=chrome-mobile&ie=UTF-8#q=cards+stores+in+okc&istate=lrl:xpd"));
        mVendors.add(new Vendor("Jewelry","https://www.google.com/search?q=jewelers&oq=jewelers&aqs=chrome..69i57.5396j0j4&sourceid=chrome-mobile&ie=UTF-8#q=jewelers+in+okc&istate=lrl:xpd"));
        mVendors.add(new Vendor("Lighting & Decor","https://www.google.com/search?q=lighting+storesr&oq=lighting+storesr&aqs=chrome..69i57.5396j0j4&sourceid=chrome-mobile&ie=UTF-8#q=lighting+stores+in+okc&istate=lrl:xpd"));
        mVendors.add(new Vendor("Men's Formal Wear","https://www.google.com/search?q=formal+wears&oq=formal+wears&aqs=chrome..69i57.5396j0j4&sourceid=chrome-mobile&ie=UTF-8#q=formal+wears+in+okc&istate=lrl:xpd"));



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
