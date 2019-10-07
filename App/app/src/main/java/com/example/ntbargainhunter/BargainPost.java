package com.example.ntbargainhunter;


import java.util.Date;

public class BargainPost extends BargainPostId {

    public String user_id, image_url, category, desc, expiry, image_thumb, title;
    public Date timestamp;

    public BargainPost() {}

    public BargainPost(String user_id, String image_url, String category, String desc, String expiry, String title, String image_thumb, Date timestamp) {
        this.user_id = user_id;
        this.title = title;
        this.image_url = image_url;
        this.desc = desc;
        this.category = category;
        this.expiry = expiry;
        this.image_thumb = image_thumb;
        this.timestamp = timestamp;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) { this.title = title; }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) { this.expiry = expiry; }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage_thumb() {
        return image_thumb;
    }

    public void setImage_thumb(String image_thumb) {
        this.image_thumb = image_thumb;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


}
