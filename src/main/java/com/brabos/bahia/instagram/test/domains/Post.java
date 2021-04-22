package com.brabos.bahia.instagram.test.domains;


import com.brabos.bahia.instagram.test.services.UserProfileService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;


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
    private Long likes = 0L;

    @Transient
    private Boolean isLiked;

    private String  username;
    private String userImageUrl;

    @ManyToMany
    @JoinTable(name = "liked_posts", joinColumns = {
            @JoinColumn(name = "post_id")},
            inverseJoinColumns = {
            @JoinColumn(name = "user_id")
            })
    private List<UserProfile> usersLiked;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    public Post() {
    }

    public Post(Long id, String description, String imageUrl, UserProfile userProfile) {
        this.id = id;
        this.description = description;
        this.imageUrl = imageUrl;
        this.userProfile = userProfile;

    }

    public String getUsername() {
        return userProfile.getUsername();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserImageUrl() {
        return userProfile.getImageUrl();
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    @JsonIgnore
    public List<UserProfile> getUsersLiked() {
        return usersLiked;
    }

    public void addLike(UserProfile user){
        usersLiked.add(user);
        if (likes == null) likes = 1L;
        else likes ++;
    }

    public void removeLike(UserProfile user){
        usersLiked.remove(user);
        likes --;
    }

    public void setUsersLiked(List<UserProfile> usersLiked) {
        this.usersLiked = usersLiked;
    }



    @JsonIgnore
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

    public Boolean getLiked() {
        return isLiked;
    }

    public void setLiked(Boolean liked) {
        isLiked = liked;
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
