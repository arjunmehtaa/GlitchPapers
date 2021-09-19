package com.example.arjuj.glitchpapers.Model;

/**
 * Created by arjuj on 2020-05-08.
 */

public class CategoryItem {
    public String imageLink,catergoryId;

    public CategoryItem(String imageLink, String catergoryId) {
        this.imageLink = imageLink;
        this.catergoryId = catergoryId;
    }

    public CategoryItem(){

    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getcatergoryId() {
        return catergoryId;
    }

    public void setcatergoryId(String catergoryId) {
        this.catergoryId = catergoryId;
    }
}
