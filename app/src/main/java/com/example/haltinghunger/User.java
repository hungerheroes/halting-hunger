package com.example.haltinghunger;

import org.json.JSONException;
import org.json.JSONObject;

enum Role {
    DONOR, BENEFICIARY
}

public class User {
    public String name;
    public Role role;

    public static User fromJson(JSONObject jsonObject) throws JSONException {
        User user = new User();
        user.name = jsonObject.getString("name");
        user.role = Role.valueOf(jsonObject.getString("screen_name").toUpperCase());
        return user;
    }
}
