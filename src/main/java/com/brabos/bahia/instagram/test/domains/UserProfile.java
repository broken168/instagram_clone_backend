package com.brabos.bahia.instagram.test.domains;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private Long seguidores;
    private Long seguindo;
    private Long postagens;

    @OneToMany(mappedBy = "userProfile")
    private List<Post> posts = new ArrayList<>();

    public UserProfile() {
    }

    public UserProfile(Long id, String email, String username, String password, String url_image, Long seguidores, Long seguindo, Long postagens) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.url_image = url_image;
        this.seguidores = seguidores;
        this.seguindo = seguindo;
        this.postagens = postagens;

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

    public Long getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(Long seguidores) {
        this.seguidores = seguidores;
    }

    public Long getSeguindo() {
        return seguindo;
    }

    public void setSeguindo(Long seguindo) {
        this.seguindo = seguindo;
    }

    public Long getPostagens() {
        return postagens;
    }

    public void setPostagens(Long postagens) {
        this.postagens = postagens;
    }

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
