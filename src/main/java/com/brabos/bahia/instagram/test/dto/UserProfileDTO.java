package com.brabos.bahia.instagram.test.dto;

import com.brabos.bahia.instagram.test.domains.UserProfile;

import javax.swing.text.html.Option;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserProfileDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String email;
    private String username;
    private String url_image;

    private Set<Long> followers = new HashSet<>();
    private Set<Long> following = new HashSet<>();

    public UserProfileDTO() {
    }

    public UserProfileDTO(UserProfile user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.url_image = user.getUrl_image();
        this.followers = user.getFollowers();
        this.following = user.getFollowing();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public Set<Long> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<Long> followers) {
        this.followers = followers;
    }

    public Set<Long> getFollowing() {
        return following;
    }

    public void setFollowing(Set<Long> following) {
        this.following = following;
    }
}
