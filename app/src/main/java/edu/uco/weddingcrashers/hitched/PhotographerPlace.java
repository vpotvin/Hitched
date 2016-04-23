package edu.uco.weddingcrashers.hitched;

/**
 * Created by PC User on 2/19/2016.
 */
public class PhotographerPlace {
    private String mAddress;
    private String mName;
    private String mRating;
    private String mPriceLevel;
    private String mID;
    private String iconURL;
    private PhotographerReviewList mPhotographerReviewList;

    public PhotographerReviewList getReviewList() {
        return mPhotographerReviewList;
    }

    public void setReviewList(PhotographerReviewList photographerReviewList) {
        mPhotographerReviewList = photographerReviewList;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    public String getID() {
        return mID;
    }

    public void setID(String ID) {
        mID = ID;
    }


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
