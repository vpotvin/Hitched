package edu.uco.weddingcrashers.hitched;

import java.util.UUID;

/**
 * Created by Tung Nguyen on 2/4/2016.
 */
public class CakeV {
    private UUID mVendorID;
    private String mVendorName;
    private String mVendorContact;
    private String mVendorWebsite;

    public String getQuery() {
        return mQuery;
    }

    public void setQuery(String query) {
        mQuery = query;
    }

    private String mQuery;


    public CakeV(){}

    public CakeV(String Name, String Query) {
        mVendorName = Name;
        mQuery = Query;
        mVendorID = UUID.randomUUID();
    }

    public UUID getVendorID() {
        return mVendorID;
    }

    public String getVendorName() {
        return mVendorName;
    }

    public void setVendorName(String vendorName) {
        mVendorName = vendorName;
    }

    public String getVendorContact() {
        return mVendorContact;
    }

    public void setVendorContact(String vendorContact) {
        mVendorContact = vendorContact;
    }

    public String getVendorWebsite() {
        return mVendorWebsite;
    }

    public void setVendorWebsite(String vendorWebsite) {
        mVendorWebsite = vendorWebsite;
    }
}
