package com.brabos.bahia.instagram.test.resources;

import com.brabos.bahia.instagram.test.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserProfileResource {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(userProfileService.find(id));
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok().body(userProfileService.findAll());
    }
}
