package edu.uco.weddingcrashers.hitched;

import android.content.Context;
import android.util.Log;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC User on 2/18/2016.
 */
public class PhotographerSavedList {
    private static PhotographerSavedList sPhotographerSavedList;
    private List<PhotographerSaved> mPhotographerSaveds;


    public static PhotographerSavedList get(Context context){
        if(sPhotographerSavedList == null){
            sPhotographerSavedList = new PhotographerSavedList(context);
        }
        return sPhotographerSavedList;
    }

    public void setFavoriteList(){
        ParseQuery query = new ParseQuery("FavoriteVendors");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> vendorList, ParseException e) {
                if (e == null) {
                    Log.d("FavoriteVendor", "Retrieved " + vendorList.size() + " vendors");
                    for(int i = 0;i<vendorList.size();i++){
                        PhotographerSaved photographerSaved = new PhotographerSaved();
                        photographerSaved.setId(vendorList.get(i).getString("vendorID"));
                        photographerSaved.setName(vendorList.get(i).getString("name"));
                        photographerSaved.setRating(vendorList.get(i).getString("rating"));
                        photographerSaved.setImgURL(vendorList.get(i).getString("imgURL"));
                        photographerSaved.setPhone(vendorList.get(i).getString("phone"));
                        photographerSaved.setAddress(vendorList.get(i).getString("address"));
                        photographerSaved.setWebsite(vendorList.get(i).getString("website"));
                        mPhotographerSaveds.add(photographerSaved);
                    }
                } else {
                    Log.d("FavoriteVendor", "Error: " + e.getMessage());
                }
            }
        });


    }

    public void deleteDataFromDatabase(String tableName){
        ParseQuery query = new ParseQuery(tableName);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> vendorList, ParseException e) {
                if (e == null) {
                    Log.d("FavoriteVendor", "Retrieved " + vendorList.size() + " vendors");
                    for(int i = 0;i<vendorList.size();i++){
                       vendorList.get(i).deleteInBackground(new DeleteCallback() {
                           @Override
                           public void done(ParseException e) {
                               Log.i("FavoriteVendor","Delete Successfully");
                           }
                       });
                    }
                } else {
                    Log.d("FavoriteVendor", "Error: " + e.getMessage());
                }
            }
        });

    }

    private PhotographerSavedList(Context context){
        mPhotographerSaveds = new ArrayList<>();


    }

    public List<PhotographerSaved> getSavedVendors(){
        return mPhotographerSaveds;
    }

    public PhotographerSaved getSavedVendor(String id){
        for (PhotographerSaved photographerSaved : mPhotographerSaveds){
            if(photographerSaved.getId().equals(id)){
                return photographerSaved;
            }
        }
        return null;
    }

    public boolean checkForItemExist(PhotographerSaved photographerSaved){
        if(getSavedVendor(photographerSaved.getId()) == null)
            return true;
        return false;
    }
    public boolean addSavedVendor(PhotographerSaved photographerSaved){
        if(getSavedVendor(photographerSaved.getId())==null) {
            mPhotographerSaveds.add(photographerSaved);
            return true;
        }
        else
            return false;

    }
}
