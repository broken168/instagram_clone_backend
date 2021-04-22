package com.brabos.bahia.instagram.test.services;

import com.brabos.bahia.instagram.test.domains.Comment;
import com.brabos.bahia.instagram.test.domains.Post;
import com.brabos.bahia.instagram.test.domains.UserProfile;
import com.brabos.bahia.instagram.test.dto.NewCommentDTO;
import com.brabos.bahia.instagram.test.repositories.CommentRepository;
import com.brabos.bahia.instagram.test.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private PostServices postServices;

    @Transactional
    public Comment addComment(Long postId, NewCommentDTO newCommentDTO) {
        UserProfile currentUser = userProfileService.getCurrentUser();
        Comment comment = new Comment(null, currentUser, newCommentDTO.getMsg(), new Date(), postServices.findById(postId));
        return commentRepository.save(comment);
    }

    public Page<Comment> findCommentsByPost(Long postId, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);
        postServices.findById(postId);
        return commentRepository.findCommentsByPost(postId, pageRequest);

    }

    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Nenhum post encontrado para esse id: " + id));
    }
}
