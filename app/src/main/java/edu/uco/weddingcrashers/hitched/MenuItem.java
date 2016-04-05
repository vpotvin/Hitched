package edu.uco.weddingcrashers.hitched;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by drenf on 2/2/2016.
 */
@ParseClassName("MenuItem")
public class MenuItem extends ParseObject
{
    public MenuItem()
    {}

    public MenuItem(String item) {
        this();
        this.setItem(item);

    }

    public String getItem() {
        return getString("item");
    }

    public void setItem(String item) {
        put("item", item);

    }
}


