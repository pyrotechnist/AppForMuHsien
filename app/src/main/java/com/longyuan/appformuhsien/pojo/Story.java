package com.longyuan.appformuhsien.pojo;

/**
 * Created by LONGYUAN on 2018/1/9.
 */

public class Story {

    public Story(String title, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public Story(String id, String title, String imageUrl) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    private String id;

    private String title;

    private String imageUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
