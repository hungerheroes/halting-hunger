package com.example.haltinghunger;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

@ParseClassName("Post")
public class FoodPost extends ParseObject {
    public static final String KEY_ID="objectId";
    public static final String KEY_DONOR = "donor";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DETAILS = "details";
    public static final String KEY_QUANTITY = "quantity";
    public static final String KEY_NV = "nonvegetarian";
    public static final String KEY_HM = "homemade";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_ZIPCODE = "zipcode";
    public static final String KEY_IMAGE= "image";

    public static final String KEY_STARTDATE="start_date";
    public static final String KEY_STARTTIME="start_time";
    public static final String KEY_ENDDATE="end_date";
    public static final String KEY_ENDTIME="end_time";

    public static final String KEY_STATUS= "status";
    public static final String KEY_CREATED_KEY="createdAt";
    public static final String KEY_VOLUNTEER="beneficiary";

    public String getId() {
        return getString(KEY_ID);
    }

    public ParseUser getDonor() {
        return getParseUser(KEY_DONOR);
    }
    public void setDonor(ParseUser donor) {
        put(KEY_DONOR, donor);
    }

    public ParseUser getVolunteer() {
        return getParseUser(KEY_VOLUNTEER);
    }
    public void setVolunteer(ParseUser volunteer) {
        put(KEY_VOLUNTEER, volunteer);
    }

    public String getTitle() {
        return getString(KEY_TITLE);
    }
    public void setTitle(String title) {
        put(KEY_TITLE, title);
    }

    public String getDetails() {
        return getString(KEY_DETAILS);
    }
    public void setDetails(String details) {
        put(KEY_DETAILS, details);
    }

    public String getQuantity() {
        return getString(KEY_QUANTITY);
    }
    public void setQuantity(String quantity) {
        put(KEY_QUANTITY, quantity);
    }

    public Boolean getNV() {
        return getBoolean(KEY_NV);
    }
    public void setNV(Boolean nv) {
        put(KEY_NV, nv);
    }

    public Boolean getHM() {
        return getBoolean(KEY_HM);
    }
    public void setHM(Boolean hm) {
        put(KEY_HM, hm);
    }

    public String getLocation() {
        return getString(KEY_LOCATION);
    }
    public void setLocation(String location) {
        put(KEY_LOCATION, location);
    }

    public int getZipCode() {
        return getInt(KEY_ZIPCODE);
    }
    public void setZipCode(Integer zipCode) {
        put(KEY_ZIPCODE, zipCode);
    }

    public ParseFile getImage(){
        return  getParseFile(KEY_IMAGE);
    }
    public void setImage(ParseFile parseFile){
        put(KEY_IMAGE,parseFile);
    }

    public String getStartDate() {
        return getString(KEY_STARTDATE);
    }
    public void setStartDate(String start_date) {
        put(KEY_STARTDATE, start_date);
    }

    public String getStartTime() {
        return getString(KEY_STARTTIME);
    }
    public void setStartTime(String start_time) {
        put(KEY_STARTTIME, start_time);
    }

    public String getEndDate() {
        return getString(KEY_ENDDATE);
    }
    public void setEndDate(String end_date) {
        put(KEY_ENDDATE, end_date);
    }

    public String getEndTime() {
        return getString(KEY_ENDTIME);
    }
    public void setEndTime(String end_time) {
        put(KEY_ENDTIME, end_time);
    }

    public String getStatus() {
        return getString(KEY_STATUS);
    }
    public void setStatus(String status) {
        put(KEY_STATUS, status);
    }

    public Date getTime(){
        return getDate(KEY_CREATED_KEY);
    }
}
