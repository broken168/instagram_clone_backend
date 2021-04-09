package com.brabos.bahia.instagram.test.dto;

import com.brabos.bahia.instagram.test.domains.UserProfile;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class NewPostDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "Campo obrigatório")
    private String description;

    @URL(message = "URL inválida")
    @NotEmpty(message = "Campo obrigatório")
    private String imageUrl;

    @NotNull(message = "É preciso informar um id de usuário")
    private UserProfile userProfile;

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

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
