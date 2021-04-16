package com.example.haltinghunger;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

@ParseClassName("User")
public class UserClass extends ParseObject{
    public static final String KEY_TYPE="type";

    public String getType(){
        return getString(KEY_TYPE);
    }
}

