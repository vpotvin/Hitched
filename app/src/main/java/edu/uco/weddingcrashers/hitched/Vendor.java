package edu.uco.weddingcrashers.hitched;

import android.graphics.drawable.Drawable;

import java.util.UUID;

/**
 * Created by Tung Nguyen on 2/4/2016.
 */
public class Vendor {
    private UUID mVendorID;
    private String mVendorName;
    private String mVendorContact;
    private String mVendorWebsite;
    private String mCategory;
    private Drawable mImage;

    public Drawable getImage() {
        return mImage;
    }

    public void setImage(Drawable image) {
        mImage = image;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public String getQuery() {
        return mQuery;
    }

    public void setQuery(String query) {
        mQuery = query;
    }

    private String mQuery;


    public Vendor(){}

    public Vendor(String Name,String Query) {
        mVendorName = Name;
        mQuery = Query;
        mVendorID = UUID.randomUUID();
    }
    public Vendor(String Name,String Query,String category) {
        mVendorName = Name;
        mQuery = Query;
        mVendorID = UUID.randomUUID();
        mCategory = category;

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
    @Override
    public String toString(){
        return mCategory;
    }
}
