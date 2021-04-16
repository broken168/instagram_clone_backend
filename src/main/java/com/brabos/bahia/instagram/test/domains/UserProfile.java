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
    private String imageUrl;
    private Long postsNumber;

    @OneToMany(mappedBy = "userProfile")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "userProfile")
    private List<Comment> comments = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "follower")
    private Set<Long> followers = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "following")
    private Set<Long> following = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "profiles")
    private Set<Integer> profiles = new HashSet<>();

    public UserProfile() {
        addProfiles(Profile.CLIENT);
    }

    public UserProfile(Long id, String email, String username, String password, String imageUrl) {
        addProfiles(Profile.CLIENT);
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.imageUrl = imageUrl;
    }

    @JsonIgnore
    public Set<Profile> getProfiles() {
        return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    @JsonIgnore
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addProfiles(Profile profile) {
        this.profiles.add(profile.getCode());
    }

    public Set<Long> getFollowing() {
        return following;
    }

    public void addFollowging(Long id){
        following.add(id);
    }

    public void setFollowing(Set<Long> following) {
        this.following = following;
    }

    //remove a following from following list
    public void removeFollowing(Long id){
        following.remove(id);
    }

    //return the id of each user
    public Set<Long> getFollowers() {
        return followers;
    }

    //adds a new follower to follower list
    public void addFollowers(Long id){
        followers.add(id);
    }

    //remove a follower from follower list
    public void removeFollowers(Long id){
        followers.remove(id);
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Long getPostsNumber() {
        return postsNumber;
    }

    public void setPostsNumber(Long postsNumber) {
        this.postsNumber = postsNumber;
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
