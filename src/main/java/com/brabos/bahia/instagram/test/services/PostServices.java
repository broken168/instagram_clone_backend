package com.brabos.bahia.instagram.test.services;

import com.brabos.bahia.instagram.test.domains.Post;
import com.brabos.bahia.instagram.test.domains.UserProfile;
import com.brabos.bahia.instagram.test.dto.NewPostDTO;
import com.brabos.bahia.instagram.test.repositories.PostRepository;
import com.brabos.bahia.instagram.test.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PostServices {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserProfileService userProfileService;

    public Post findById(Long id){
        Optional<Post> post = postRepository.findById(id);
        return post.orElseThrow(() -> new ObjectNotFoundException("Nenhum post encontrado para esse id: " + id));
    }

    @Transactional
    public Post insert(Post post) {
        return postRepository.save(post);
    }

    public Post fromDTO(NewPostDTO newPostDTO) {
        UserProfile userProfile = userProfileService.findById(newPostDTO.getUserProfileId());
        return new Post(null, newPostDTO.getDescription(), newPostDTO.getImageUrl(), userProfile);
    }


}
