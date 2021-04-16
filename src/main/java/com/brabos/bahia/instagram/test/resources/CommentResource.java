package com.brabos.bahia.instagram.test.resources;

import com.brabos.bahia.instagram.test.domains.Comment;
import com.brabos.bahia.instagram.test.dto.NewCommentDTO;
import com.brabos.bahia.instagram.test.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/comment")
public class CommentResource {

    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/post/{id}")
    public ResponseEntity<Page<Comment>> findCommentsByPost(@PathVariable("id") Long postId,
                                                   @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage,
                                                   @RequestParam(value = "orderBy", defaultValue = "id")String orderBy,
                                                   @RequestParam(value = "direction", defaultValue = "DESC")String direction){
        return ResponseEntity.ok().body(commentService.findCommentsByPost(postId, page, linesPerPage, orderBy, direction));
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Void> addComment(@PathVariable("id") Long postId, @RequestBody NewCommentDTO newCommentDTO){
        Comment comment = commentService.addComment(postId, newCommentDTO);
        URI uri  = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(comment.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
