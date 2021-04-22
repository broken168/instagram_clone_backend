package com.brabos.bahia.instagram.test.dto;

import java.io.Serializable;

public class PostsByUserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String imageUrl;

    public PostsByUserDTO() {
    }

    public PostsByUserDTO(Long id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
