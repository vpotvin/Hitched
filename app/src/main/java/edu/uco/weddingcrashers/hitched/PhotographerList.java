package edu.uco.weddingcrashers.hitched;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Tung Nguyen on 2/4/2016.
 */
public class PhotographerList {
    private static PhotographerList sPhotographerList;
    private List<PhotographerV> mPhotographerVs;

    public static PhotographerList get(Context context){
        if(sPhotographerList == null){
            sPhotographerList = new PhotographerList(context);
        }
        return sPhotographerList;
    }

    private PhotographerList(Context context){
        mPhotographerVs = new ArrayList<>();
        mPhotographerVs.add(new PhotographerV("Alabama","photographer+in+Alabama"));
        mPhotographerVs.add(new PhotographerV("Alaska ","photographer+in+Alaska"));
        mPhotographerVs.add(new PhotographerV("Arizona ","photographer+in+Arizona"));
        mPhotographerVs.add(new PhotographerV("Arkansas ","photographer+in+Arkansas"));
        mPhotographerVs.add(new PhotographerV("California ","photographer+in+California"));
        mPhotographerVs.add(new PhotographerV("Colorado ","photographer+in+Colorado"));
        mPhotographerVs.add(new PhotographerV("Connecticut ","photographer+in+Connecticut"));
        mPhotographerVs.add(new PhotographerV("Delaware ","photographer+in+Delaware"));
        mPhotographerVs.add(new PhotographerV("Florida ","photographer+in+Florida"));
        mPhotographerVs.add(new PhotographerV("Georgia ","photographer+in+Georgia"));
        mPhotographerVs.add(new PhotographerV("Hawaii ","photographer+in+Hawaii"));
        mPhotographerVs.add(new PhotographerV("Idaho ","photographer+in+Idaho"));
        mPhotographerVs.add(new PhotographerV("Illinois","photographer+in+Illinois"));
        mPhotographerVs.add(new PhotographerV("Indiana ","photographer+in+Indiana"));
        mPhotographerVs.add(new PhotographerV("Iowa ","photographer+in+Iowa"));
        mPhotographerVs.add(new PhotographerV("Kansas ","photographer+in+Kansas"));
        mPhotographerVs.add(new PhotographerV("Kentucky ","photographer+in+Kentucky"));
        mPhotographerVs.add(new PhotographerV("Louisiana ","photographer+in+Louisiana"));
        mPhotographerVs.add(new PhotographerV("Maine ","photographer+in+Maine"));
        mPhotographerVs.add(new PhotographerV("Maryland ","photographer+in+Maryland"));
        mPhotographerVs.add(new PhotographerV("Massachusetts ","photographer+in+Massachusetts"));
        mPhotographerVs.add(new PhotographerV("Michigan ","photographer+in+Michigan"));
        mPhotographerVs.add(new PhotographerV("Minnesota ","photographer+in+Minnesota"));
        mPhotographerVs.add(new PhotographerV("Mississippi ","photographer+in+Mississippi"));
        mPhotographerVs.add(new PhotographerV("Missouri ","photographer+in+Missouri"));
        mPhotographerVs.add(new PhotographerV("Montana","photographer+in+Montana"));
        mPhotographerVs.add(new PhotographerV("Nebraska ","photographer+in+Nebraska"));
        mPhotographerVs.add(new PhotographerV("Nevada ","photographer+in+Nevada"));
        mPhotographerVs.add(new PhotographerV("New Hampshire ","photographer+in+New+Hampshire"));
        mPhotographerVs.add(new PhotographerV("New Jersey ","photographer+in+New+Jersey"));
        mPhotographerVs.add(new PhotographerV("New Mexico ","photographer+in+New+Mexico"));
        mPhotographerVs.add(new PhotographerV("New York ","photographer+in+new+york"));
        mPhotographerVs.add(new PhotographerV("North Carolina ","photographer+in+north+carolina"));
        mPhotographerVs.add(new PhotographerV("North Dakota ","photographer+in+north+dakota"));
        mPhotographerVs.add(new PhotographerV("Ohio ","photographer+in+ohio"));
        mPhotographerVs.add(new PhotographerV("Oklahoma ","photographer+in+oklahoma"));
        mPhotographerVs.add(new PhotographerV("Oregon ","photographer+in+oregon"));
        mPhotographerVs.add(new PhotographerV("Pennsylvania","photographer+in+pennsylvania"));
        mPhotographerVs.add(new PhotographerV("Rhode Island ","photographer+in+rhode+island"));
        mPhotographerVs.add(new PhotographerV("South Carolina ","photographer+in+souch+carolina"));
        mPhotographerVs.add(new PhotographerV("South Dakota ","photographer+in+south+dakota"));
        mPhotographerVs.add(new PhotographerV("Tennessee ","photographer+in+tennessee"));
        mPhotographerVs.add(new PhotographerV("Texas ","photographer+in+texas"));
        mPhotographerVs.add(new PhotographerV("Utah ","photographer+in+utah"));
        mPhotographerVs.add(new PhotographerV("Vermont ","photographer+in+vermont"));
        mPhotographerVs.add(new PhotographerV("Virginia ","photographer+in+virginia"));
        mPhotographerVs.add(new PhotographerV("Washington ","photographer+in+washington"));
        mPhotographerVs.add(new PhotographerV("West Virginia ","photographer+in+west+virginia"));
        mPhotographerVs.add(new PhotographerV("Wisconsin ","photographer+in+wisconsin"));
        mPhotographerVs.add(new PhotographerV("Wyoming","photographer+in+wyoming"));

    }

    public List<PhotographerV> getVendors(){
        return mPhotographerVs;
    }

    public PhotographerV getVendor(UUID id){
        for (PhotographerV photographerV : mPhotographerVs){
            if(photographerV.getVendorID().equals(id)){
                return photographerV;
            }
        }
        return null;
    }

    public void addVendor(PhotographerV photographerV){
        mPhotographerVs.add(photographerV);
    }


}
