package edu.uco.weddingcrashers.hitched;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CakeList {
    private static CakeList sCakeList;
    private List<CakeV> mCakeVs;

    public static CakeList get(Context context){
        if(sCakeList == null){
            sCakeList = new CakeList(context);
        }
        return sCakeList;
    }

    private CakeList(Context context){
        mCakeVs = new ArrayList<>();
        mCakeVs.add(new CakeV("Alabama","bakery+in+Alabama"));
        mCakeVs.add(new CakeV("Alaska ","bakery+in+Alaska"));
        mCakeVs.add(new CakeV("Arizona ","bakery+in+Arizona"));
        mCakeVs.add(new CakeV("Arkansas ","bakery+in+Arkansas"));
        mCakeVs.add(new CakeV("California ","bakery+in+California"));
        mCakeVs.add(new CakeV("Colorado ","bakery+in+Colorado"));
        mCakeVs.add(new CakeV("Connecticut ","bakery+in+Connecticut"));
        mCakeVs.add(new CakeV("Delaware ","bakery+in+Delaware"));
        mCakeVs.add(new CakeV("Florida ","bakery+in+Florida"));
        mCakeVs.add(new CakeV("Georgia ","bakery+in+Georgia"));
        mCakeVs.add(new CakeV("Hawaii ","bakery+in+Hawaii"));
        mCakeVs.add(new CakeV("Idaho ","bakery+in+Idaho"));
        mCakeVs.add(new CakeV("Illinois","bakery+in+Illinois"));
        mCakeVs.add(new CakeV("Indiana ","bakery+in+Indiana"));
        mCakeVs.add(new CakeV("Iowa ","bakery+in+Iowa"));
        mCakeVs.add(new CakeV("Kansas ","bakery+in+Kansas"));
        mCakeVs.add(new CakeV("Kentucky ","bakery+in+Kentucky"));
        mCakeVs.add(new CakeV("Louisiana ","bakery+in+Louisiana"));
        mCakeVs.add(new CakeV("Maine ","bakery+in+Maine"));
        mCakeVs.add(new CakeV("Maryland ","bakery+in+Maryland"));
        mCakeVs.add(new CakeV("Massachusetts ","bakery+in+Massachusetts"));
        mCakeVs.add(new CakeV("Michigan ","bakery+in+Michigan"));
        mCakeVs.add(new CakeV("Minnesota ","bakery+in+Minnesota"));
        mCakeVs.add(new CakeV("Mississippi ","bakery+in+Mississippi"));
        mCakeVs.add(new CakeV("Missouri ","bakery+in+Missouri"));
        mCakeVs.add(new CakeV("Montana","bakery+in+Montana"));
        mCakeVs.add(new CakeV("Nebraska ","bakery+in+Nebraska"));
        mCakeVs.add(new CakeV("Nevada ","bakery+in+Nevada"));
        mCakeVs.add(new CakeV("New Hampshire ","bakery+in+New+Hampshire"));
        mCakeVs.add(new CakeV("New Jersey ","bakery+in+New+Jersey"));
        mCakeVs.add(new CakeV("New Mexico ","bakery+in+New+Mexico"));
        mCakeVs.add(new CakeV("New York ","bakery+in+new+york"));
        mCakeVs.add(new CakeV("North Carolina ","bakery+in+north+carolina"));
        mCakeVs.add(new CakeV("North Dakota ","bakery+in+north+dakota"));
        mCakeVs.add(new CakeV("Ohio ","bakery+in+ohio"));
        mCakeVs.add(new CakeV("Oklahoma ","bakery+in+oklahoma"));
        mCakeVs.add(new CakeV("Oregon ","bakery+in+oregon"));
        mCakeVs.add(new CakeV("Pennsylvania","bakery+in+pennsylvania"));
        mCakeVs.add(new CakeV("Rhode Island ","bakery+in+rhode+island"));
        mCakeVs.add(new CakeV("South Carolina ","bakery+in+souch+carolina"));
        mCakeVs.add(new CakeV("South Dakota ","bakery+in+south+dakota"));
        mCakeVs.add(new CakeV("Tennessee ","bakery+in+tennessee"));
        mCakeVs.add(new CakeV("Texas ","bakery+in+texas"));
        mCakeVs.add(new CakeV("Utah ","bakery+in+utah"));
        mCakeVs.add(new CakeV("Vermont ","bakery+in+vermont"));
        mCakeVs.add(new CakeV("Virginia ","bakery+in+virginia"));
        mCakeVs.add(new CakeV("Washington ","bakery+in+washington"));
        mCakeVs.add(new CakeV("West Virginia ","bakery+in+west+virginia"));
        mCakeVs.add(new CakeV("Wisconsin ","bakery+in+wisconsin"));
        mCakeVs.add(new CakeV("Wyoming","bakery+in+wyoming"));

    }

    public List<CakeV> getVendors(){
        return mCakeVs;
    }

    public CakeV getVendor(UUID id){
        for (CakeV cakeV : mCakeVs){
            if(cakeV.getVendorID().equals(id)){
                return cakeV;
            }
        }
        return null;
    }

    public void addVendor(CakeV cakeV){
        mCakeVs.add(cakeV);
    }


}
