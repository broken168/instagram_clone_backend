package com.brabos.bahia.instagram.test.dto;

import com.brabos.bahia.instagram.test.domains.Post;
import com.brabos.bahia.instagram.test.domains.UserProfile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserProfileSearchPostDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String imageUrl;
    private List<Post> posts = new ArrayList<>();

    public UserProfileSearchPostDTO() {
    }

    public UserProfileSearchPostDTO(UserProfile user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.imageUrl = user.getImageUrl();
        this.posts = user.getPosts();
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
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
