package com.brabos.bahia.instagram.test.domains;


import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String imageUrl;
    private Long likes;

    @ManyToMany
    @JoinTable(name = "liked_posts", joinColumns = {
            @JoinColumn(name = "user_id")},
            inverseJoinColumns = {
            @JoinColumn(name = "post_id")
            })
    private List<UserProfile> usersLiked;

    private boolean isLiked;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    public Post() {
    }

    public Post(UserProfile user) {
        if (usersLiked.contains(user)) setLiked(true);
        else setLiked(false);
    }

    public Post(Long id, String description, String imageUrl, UserProfile userProfile) {
        this.id = id;
        this.description = description;
        this.imageUrl = imageUrl;
        this.userProfile = userProfile;

    }

    @JsonIgnore
    public List<UserProfile> getUsersLiked() {
        return usersLiked;
    }

    public void addLike(UserProfile user){
        likes++;
        usersLiked.add(user);
    }

    public void setUsersLiked(List<UserProfile> usersLiked) {
        this.usersLiked = usersLiked;
    }

    public boolean getIsLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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

    @JsonIgnore
    public UserProfile getUserProfile() {
        return userProfile;
    }

    @JsonIgnore
    public Long getIdUser(){
        return userProfile.getId();
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
