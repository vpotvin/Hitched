package edu.uco.weddingcrashers.hitched;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by vdpotvin on 3/18/16.
 */

@ParseClassName("GuestTable")
public class Table extends ParseObject {

    public void setXCoordinate(int xCoordinate) {
        put("x_coord", xCoordinate);
    }

    public int getXCoordinate() {
        return getInt("x_coord");
    }
    public void setYCoordinate(int yCoordinate) {
        put("y_coord", yCoordinate);
    }

    public int getYCoordinate() {
        return getInt("y_coord");
    }

    public void setMaxGuests(int max) {
        put("max_guests", max);
    }

    public int getMaxGuests() {
        return getInt("max_guests");
    }

    public void setGuests(List<String> guest){
        put("guests", guest);
    }

    public List<String> getGuests(){
        return getList("guests");
    }
}
