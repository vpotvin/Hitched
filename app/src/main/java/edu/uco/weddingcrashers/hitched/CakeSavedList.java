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
public class CakeSavedList {
    private static CakeSavedList sCakeSavedList;
    private List<CakeSaved> mCakeSaveds;


    public static CakeSavedList get(Context context){
        if(sCakeSavedList == null){
            sCakeSavedList = new CakeSavedList(context);
        }
        return sCakeSavedList;
    }

    public void setFavoriteList(){
        ParseQuery query = new ParseQuery("FavoriteVendors");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> vendorList, ParseException e) {
                if (e == null) {
                    Log.d("FavoriteVendor", "Retrieved " + vendorList.size() + " vendors");
                    for(int i = 0;i<vendorList.size();i++){
                        CakeSaved cakeSaved = new CakeSaved();
                        cakeSaved.setId(vendorList.get(i).getString("vendorID"));
                        cakeSaved.setName(vendorList.get(i).getString("name"));
                        cakeSaved.setRating(vendorList.get(i).getString("rating"));
                        cakeSaved.setImgURL(vendorList.get(i).getString("imgURL"));
                        cakeSaved.setPhone(vendorList.get(i).getString("phone"));
                        cakeSaved.setAddress(vendorList.get(i).getString("address"));
                        cakeSaved.setWebsite(vendorList.get(i).getString("website"));
                        mCakeSaveds.add(cakeSaved);
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

    private CakeSavedList(Context context){
        mCakeSaveds = new ArrayList<>();


    }

    public List<CakeSaved> getSavedVendors(){
        return mCakeSaveds;
    }

    public CakeSaved getSavedVendor(String id){
        for (CakeSaved cakeSaved : mCakeSaveds){
            if(cakeSaved.getId().equals(id)){
                return cakeSaved;
            }
        }
        return null;
    }

    public boolean checkForItemExist(CakeSaved cakeSaved){
        if(getSavedVendor(cakeSaved.getId()) == null)
            return true;
        return false;
    }
    public boolean addSavedVendor(CakeSaved cakeSaved){
        if(getSavedVendor(cakeSaved.getId())==null) {
            mCakeSaveds.add(cakeSaved);
            return true;
        }
        else
            return false;

    }
}
