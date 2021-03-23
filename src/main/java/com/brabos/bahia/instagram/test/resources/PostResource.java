package com.brabos.bahia.instagram.test.resources;

import com.brabos.bahia.instagram.test.domains.Post;
import com.brabos.bahia.instagram.test.dto.NewPostDTO;
import com.brabos.bahia.instagram.test.services.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostServices services;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(services.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> newPost(@RequestBody NewPostDTO newPostDTO){
        Post post = services.fromDTO(newPostDTO);
        post = services.insert(post);
        URI uri  = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
