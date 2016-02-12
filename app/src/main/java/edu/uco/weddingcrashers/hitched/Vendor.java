package edu.uco.weddingcrashers.hitched;

import java.util.UUID;

/**
 * Created by Tung Nguyen on 2/4/2016.
 */
public class Vendor {
    private UUID mVendorID;
    private String mVendorName;
    private String mVendorContact;
    private String mVendorWebsite;

    public Vendor(){}

    public Vendor(String Name) {
        mVendorName = Name;
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
