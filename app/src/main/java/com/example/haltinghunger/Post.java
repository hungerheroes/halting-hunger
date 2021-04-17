package com.example.haltinghunger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Post {
    public String title;
    public String[] items;
    public String created_at;
    public User user;
    public long id;

    public static Post fromJson(JSONObject jsonObject) throws JSONException {
        Post post = new Post();
        post.title = jsonObject.getString("title");
        post.created_at = jsonObject.getString("created_at");
        post.user = User.fromJson(jsonObject.getJSONObject("user"));
        post.id = jsonObject.getLong("id");
        return post;
    }

    public static List<Post> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Post> posts = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            posts.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return posts;
    }

    public String getFormattedTimestamp() {
        return TimeFormatter.getTimeDifference(created_at);
    }
}
