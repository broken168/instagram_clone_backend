package com.brabos.bahia.instagram.test.dto;

import java.io.Serializable;

public class NewPostDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String description;
    private String imageUrl;
    private Long userProfileId;

    public NewPostDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Long userProfileId) {
        this.userProfileId = userProfileId;
    }
}
