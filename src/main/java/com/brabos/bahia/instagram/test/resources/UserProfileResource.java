package com.brabos.bahia.instagram.test.resources;

import com.brabos.bahia.instagram.test.domains.UserProfile;
import com.brabos.bahia.instagram.test.dto.NewUserProfileDTO;
import com.brabos.bahia.instagram.test.dto.UserProfileDTO;
import com.brabos.bahia.instagram.test.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping(value = "/users")
public class UserProfileResource {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserProfileDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(new UserProfileDTO( userProfileService.findById(id) ));
    }

    @GetMapping(value = "/newFollow/{id}")
    public ResponseEntity<Void> newFollow(@PathVariable("id") Long id){
        userProfileService.newFollow(id);
        return ResponseEntity.noContent().build();
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

    @PostMapping
    public ResponseEntity<Void> newUser(@Valid @RequestBody NewUserProfileDTO newUserProfileDTO){
        UserProfile user = userProfileService.fromDTO(newUserProfileDTO);
        user = userProfileService.insert(user);
        URI uri  = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
