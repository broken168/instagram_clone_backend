package com.brabos.bahia.instagram.test.resources;

import com.brabos.bahia.instagram.test.domains.UserProfile;
import com.brabos.bahia.instagram.test.dto.NewUserProfileDTO;
import com.brabos.bahia.instagram.test.dto.UserProfileDTO;
import com.brabos.bahia.instagram.test.helper.URL;
import com.brabos.bahia.instagram.test.security.JWTUtil;
import com.brabos.bahia.instagram.test.security.UserSS;
import com.brabos.bahia.instagram.test.security.UserService;
import com.brabos.bahia.instagram.test.services.UserProfileService;
import com.brabos.bahia.instagram.test.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping(value = "/users")
public class UserProfileResource {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserProfileDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(new UserProfileDTO(userProfileService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<Page> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage,
                                         @RequestParam(value = "orderBy", defaultValue = "username")String orderBy,
                                         @RequestParam(value = "direction", defaultValue = "ASC")String direction)
    {
        Page<UserProfile> list = userProfileService.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/current_user")
    public ResponseEntity<UserProfile> currentUser(){
        return ResponseEntity.ok().body(userProfileService.getCurrentUser());
    }

    @GetMapping(value = {"/username/{username}"})
    public ResponseEntity<Page> findByUsername(@PathVariable("username") String username,
                                            @RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage,
                                            @RequestParam(value = "orderBy", defaultValue = "username")String orderBy,
                                            @RequestParam(value = "direction", defaultValue = "ASC")String direction){
        return ResponseEntity.ok().body(userProfileService.findByUsername(username, page, linesPerPage, orderBy, direction));
    }

    @PostMapping(value = "/new_follow/{id}")
    public ResponseEntity<Void> newFollow(@PathVariable("id") Long id){
        userProfileService.newFollow(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/remove_follow/{id}")
    public ResponseEntity<Void> removeFollow(@PathVariable("id") Long id){
        userProfileService.removeFollow(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response){
        UserSS userSS = UserService.getAuthenticatedUser();
        String token = jwtUtil.generateToken(userSS.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/new_user")
    public ResponseEntity<Void> newUser(@Valid @RequestBody NewUserProfileDTO newUserProfileDTO){
        UserProfile user = userProfileService.fromDTO(newUserProfileDTO);
        user = userProfileService.insert(user);
        URI uri  = URI.create("http://189.84.65.150:8080/users/" + user.getId());
        return ResponseEntity.created(uri).build();
    }


    @PutMapping(value = "/update")
    public ResponseEntity<UserProfile> userUpdate(@RequestBody UserProfile user){
        return ResponseEntity.ok().body(userProfileService.userUpdate(user));
    }
}
