package edu.uco.weddingcrashers.hitched;

/**
 * Created by drenf on 2/10/2016.
 */
public class GuestListItem {
    private String name;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String phoneNumber;
    private String role;
    private boolean weddingParty;

    public GuestListItem(String name, String address, String city, String state, String zipcode, String role, String phoneNumber, boolean weddingParty) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.weddingParty = weddingParty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isWeddingParty() {
        return weddingParty;
    }

    public void setWeddingParty(boolean weddingParty) {
        this.weddingParty = weddingParty;
    }
}
