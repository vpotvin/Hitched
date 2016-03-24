package edu.uco.weddingcrashers.hitched;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by drenf on 3/17/2016.
 */
@ParseClassName("WeddingVows")
public class WeddingVows extends ParseObject{
        String vows;
    public WeddingVows(){}

    public WeddingVows( String vows) {
        this();
        this.setVows(vows);
    }

    public void setVows(String vows)
    {
        put("vows", vows);
    }

    public String getVows()
    {
        return getString("vows");
    }
}
