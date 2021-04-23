package com.example.haltinghunger;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Post")
public class FoodPost extends ParseObject {
    public static final String KEY_DONOR="donor";
    public static final String KEY_TITLE="title";
    public static final String KEY_DETAILS="details";
    public static final String KEY_QUANTITY="quantity";
    public static final String KEY_NV="nonvegetarian";
    public static final String KEY_HM="homemade";
    public static final String KEY_LOCATION="location";
    public static final String KEY_ZIPCODE="zipcode";

    public ParseUser getDonor(){
        return getParseUser(KEY_DONOR);
    }

    public void setDonor(ParseUser donor){
        put(KEY_DONOR,donor);
    }

    public String getTitle(){
        return getString(KEY_TITLE);
    }

    public void setTitle(String title){
        put(KEY_TITLE,title);
    }

    public String getDetails(){
        return getString(KEY_DETAILS);
    }

    public void setDetails(String details){
        put(KEY_DETAILS,details);
    }

    public String getQuantity(){
        return getString(KEY_QUANTITY);
    }

    public void setQuantity(String quantity){
        put(KEY_QUANTITY,quantity);
    }

    public Boolean getNV(){
        return getBoolean(KEY_NV);
    }

    public void setNV(Boolean nv){
        put(KEY_NV,nv);
    }

    public Boolean getHM(){
        return getBoolean(KEY_HM);
    }

    public void setHM(Boolean hm){
        put(KEY_HM,hm);
    }

    public String getLocation(){
        return getString(KEY_LOCATION);
    }

    public void setLocation(String location){
        put(KEY_LOCATION,location);
    }

    public int getZipCode(){
        return getInt(KEY_ZIPCODE);
    }

    public void setZipCode(Integer zipCode){
        put(KEY_ZIPCODE,zipCode);
    }
}
