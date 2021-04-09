package com.brabos.bahia.instagram.test.services;

import com.brabos.bahia.instagram.test.domains.Post;
import com.brabos.bahia.instagram.test.domains.UserProfile;
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

    public List<Post> findAll(){
        return postRepository.findAll();
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
        return postRepository.search(ids, pageRequest);
    }

}
