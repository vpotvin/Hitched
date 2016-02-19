package edu.uco.weddingcrashers.hitched;

/**
 * Created by PC User on 2/19/2016.
 */
public class VendorPlace {
    private String mAddress;
    private String mName;
    private String mRating;
    private String mPriceLevel;

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getRating() {
        return mRating;
    }

    public void setRating(String rating) {
        mRating = rating;
    }

    public String getPriceLevel() {
        return mPriceLevel;
    }

    public void setPriceLevel(String priceLevel) {
        mPriceLevel = priceLevel;
    }
}
