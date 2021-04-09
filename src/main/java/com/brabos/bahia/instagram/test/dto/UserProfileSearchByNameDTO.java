package com.brabos.bahia.instagram.test.dto;

import com.brabos.bahia.instagram.test.domains.UserProfile;

import java.io.Serializable;

public class UserProfileSearchByNameDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String imageUrl;

    public UserProfileSearchByNameDTO() {
    }

    public UserProfileSearchByNameDTO(UserProfile user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.imageUrl = user.getImageUrl();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
