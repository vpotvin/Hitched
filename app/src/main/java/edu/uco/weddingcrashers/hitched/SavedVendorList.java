package edu.uco.weddingcrashers.hitched;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    }

    public List<SavedVendor> getSavedVendors(){
        return mSavedVendors;
    }

    public SavedVendor getSavedVendor(UUID id){
        for (SavedVendor savedVendor:mSavedVendors){
            if(savedVendor.getId().equals(id)){
                return savedVendor;
            }
        }
        return null;
    }

    public void addSavedVendor(SavedVendor savedVendor){
        mSavedVendors.add(savedVendor);
    }
}
