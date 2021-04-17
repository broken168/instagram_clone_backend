package com.brabos.bahia.instagram.test.services;

import com.brabos.bahia.instagram.test.domains.Post;
import com.brabos.bahia.instagram.test.dto.NewPostDTO;
import com.brabos.bahia.instagram.test.repositories.PostRepository;
import com.brabos.bahia.instagram.test.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostServices {


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserProfileService userProfileService;

    public Post findById(Long id){
        var post =  postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Nenhum post encontrado para esse id: " + id));
        if(post.getUsersLiked().contains(userProfileService.getCurrentUser())) post.setIsLiked(true);
        return post;
    }


    @Transactional
    public Post insert(Post post) {
        return postRepository.save(post);
    }

    public Post fromDTO(NewPostDTO newPostDTO) {
        return new Post(null, newPostDTO.getDescription(), newPostDTO.getImageUrl(), newPostDTO.getUserProfile());
    }


    public Page<Post> search(List<Long> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);
        var pageList = postRepository.search(ids, pageRequest);
        pageList.map(post -> {
            if(post.getUsersLiked().contains(userProfileService.getCurrentUser())) post.setIsLiked(true);
            return post;
        });
        return pageList;
    }

    @Transactional
    public void like(Long postId) {
        var post = findById(postId);
        var user = userProfileService.getCurrentUser();
        if( post.getUsersLiked().contains(user) ) post.removeLike(user);
        else post.addLike( user );
        postRepository.save(post);
    }
}
