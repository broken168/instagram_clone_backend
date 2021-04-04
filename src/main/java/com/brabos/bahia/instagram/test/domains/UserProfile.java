package com.brabos.bahia.instagram.test.domains;


import com.brabos.bahia.instagram.test.domains.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class UserProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String username;
    private String password;
    private String url_image;

    @OneToMany(mappedBy = "userProfile")
    private List<Post> posts = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "FOLLOWERS")
    private Set<Long> followers = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "FOLLOWING")
    private Set<Long> following = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "profiles")
    private Set<Integer> profiles = new HashSet<>();

    public UserProfile() {
    }

    public UserProfile(Long id, String email, String username, String password, String url_image) {
        addProfiles(Profile.CLIENT);
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.url_image = url_image;
    }

    @JsonIgnore
    public Set<Profile> getProfiles() {
        return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfiles(Profile profile) {
        this.profiles.add(profile.getCode());
    }

    public Set<Long> getFollowing() {
        return following;
    }

    public void setFollowing(Set<Long> following) {
        this.following = following;
    }

    public Set<Long> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<Long> followers) {
        this.followers = followers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
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

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    @JsonIgnore
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
