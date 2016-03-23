package edu.uco.weddingcrashers.hitched;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by drenf on 2/10/2016.
 */

@ParseClassName("GuestListItem")
public class GuestListItem extends ParseObject {

    public GuestListItem(){}

    public GuestListItem(String name, String address, String city, String state, String zipcode,
                         String role, String phoneNumber, boolean weddingParty, String email) {
        this();
        this.setName(name);
        this.setAddress(address);
        this.setCity(city);
        this.setState(state);
        this.setZipcode(zipcode);
        this.setRole(role);
        this.setPhoneNumber(phoneNumber);
        this.setWeddingParty(weddingParty);
        this.setEmail(email);
    }

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name", name);
    }

    public String getAddress() {
        return getString("address");
    }

    public void setAddress(String address) {
        put("address", address);
    }

    public String getCity() {
        return getString("city");
    }

    public void setCity(String city) {
        put("city", city);
    }

    public String getState() {
        return getString("state");
    }

    public void setState(String state) {
        put("state", state);
    }

    public String getZipcode() {
        return getString("zipcode");
    }

    public void setZipcode(String zipcode) {
        put("zipcode", zipcode);
    }

    public String getPhoneNumber() {
        return getString("phone_number");
    }

    public void setPhoneNumber(String phoneNumber) {
        put("phone_number", phoneNumber);
    }

    public String getRole() {
        return getString("role");
    }

    public void setRole(String role) {
        put("role", role);
    }

    public boolean isWeddingParty() {
        return getBoolean("wedding_party");
    }

    public void setWeddingParty(boolean weddingParty) {
        put("wedding_party", weddingParty);
    }

    public String getEmail(){
        return getString("email");
    }

    public void setEmail(String email){
        put("email", email);
    }
}
