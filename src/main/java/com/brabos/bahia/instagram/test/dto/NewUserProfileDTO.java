package com.brabos.bahia.instagram.test.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class NewUserProfileDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Email(message = "O email informado é inválido")
    @NotEmpty(message = "Campo obrigatório")
    private String email;

    @NotEmpty(message = "Campo obrigatório")
    @Length(min = 4, max = 16, message = "O seu nome deve ter entre 4 e 16 caracteres")
    private String username;

    @NotEmpty(message = "Campo obrigatório")
    @Length(min = 8, message = "O sua senha deve ter no mínimo 8 caracteres")
    private String password;

    public NewUserProfileDTO() {

    }

    public NewUserProfileDTO(Long id, String email, String username, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
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
}
