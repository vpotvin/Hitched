package edu.uco.weddingcrashers.hitched;

import android.content.Context;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC User on 2/18/2016.
 */
public class SavedVendorList {
    private static SavedVendorList sSavedVendorList;
    private List<SavedVendor> mSavedVendors;


    public static SavedVendorList get(Context context){
        if(sSavedVendorList == null){
            sSavedVendorList = new SavedVendorList(context);
        }
        return sSavedVendorList;
    }

    private SavedVendorList(Context context){
        mSavedVendors = new ArrayList<>();
        ParseQuery query = new ParseQuery("FavoriteVendors");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> vendorList, ParseException e) {
                if (e == null) {
                    Log.d("FavoriteVendor", "Retrieved " + vendorList.size() + " vendors");
                    for(int i = 0;i<vendorList.size();i++){
                        SavedVendor savedVendor = new SavedVendor();
                        savedVendor.setId(vendorList.get(i).getString("vendorID"));
                        savedVendor.setName(vendorList.get(i).getString("name"));
                        savedVendor.setRating(vendorList.get(i).getString("rating"));
                        savedVendor.setImgURL(vendorList.get(i).getString("imgURL"));
                        savedVendor.setPhone(vendorList.get(i).getString("phone"));
                        savedVendor.setAddress(vendorList.get(i).getString("address"));
                        savedVendor.setWebsite(vendorList.get(i).getString("website"));
                        mSavedVendors.add(savedVendor);
                    }
                } else {
                    Log.d("FavoriteVendor", "Error: " + e.getMessage());
                }
            }
        });

    }

    public List<SavedVendor> getSavedVendors(){
        return mSavedVendors;
    }

    public SavedVendor getSavedVendor(String id){
        for (SavedVendor savedVendor:mSavedVendors){
            if(savedVendor.getId().equals(id)){
                return savedVendor;
            }
        }
        return null;
    }

    public boolean addSavedVendor(SavedVendor savedVendor){
        if(getSavedVendor(savedVendor.getId())==null) {
            mSavedVendors.add(savedVendor);
            return true;
        }
        else
            return false;

    }
}
