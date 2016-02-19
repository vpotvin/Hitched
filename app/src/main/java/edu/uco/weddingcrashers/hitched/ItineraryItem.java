package edu.uco.weddingcrashers.hitched;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by vdpotvin on 2/18/16.
 *
 */

@ParseClassName("ItineraryItem")
public class ItineraryItem extends ParseObject {
    public String getTitle() {
        return getString("title");
    }

    public void setTitle(String title) {
        put("title", title);
    }


    public String getAssigned(){
        return getString("assigned");
    }

    public void setAssigned(String assigned) {
        put("assigned", assigned);
    }
}
