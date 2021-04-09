package com.brabos.bahia.instagram.test.services;

import com.brabos.bahia.instagram.test.domains.UserProfile;
import com.brabos.bahia.instagram.test.dto.NewUserProfileDTO;


import com.brabos.bahia.instagram.test.dto.UserProfileSearchByNameDTO;
import com.brabos.bahia.instagram.test.dto.UserProfileSearchPostDTO;
import com.brabos.bahia.instagram.test.repositories.UserProfileRepository;
import com.brabos.bahia.instagram.test.security.UserService;
import com.brabos.bahia.instagram.test.services.exceptions.AuthorizationException;
import com.brabos.bahia.instagram.test.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository repository;

    @Autowired
    private BCryptPasswordEncoder pe;

    public UserProfile findById(Long id) {
        UserProfile userProfile = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado para o id: " + id));
        return userProfile;
    }


    public List<UserProfile> findAll() {
        List<UserProfile> userProfiles = repository.findAll();
        if (userProfiles.size() == 0) {
            throw new ObjectNotFoundException("Nenhum usuário cadastrado");
        }
        return userProfiles;
    }

    public Page<UserProfile> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public UserProfile fromDTO(NewUserProfileDTO user) {
        return new UserProfile(null, user.getEmail(), user.getUsername(), pe.encode(user.getPassword()), null);
    }

    @Transactional
    public UserProfile insert(UserProfile user) {
        return repository.save(user);
    }

    @Transactional
    public void newFollow(Long id) {
        UserProfile userFollower = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado para o id: " + id));
        UserProfile userFollowing = findById(UserService.getAuthenticatedUser().getId());
        userFollowing.addFollowging(userFollower.getId());
        userFollower.addFollowers(userFollowing.getId());
        repository.saveAll(Arrays.asList(userFollower, userFollowing));
    }

    @Transactional
    public void removeFollow(Long id) {
        UserProfile userFollower = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado para o id: " + id));
        UserProfile userFollowing = findById(UserService.getAuthenticatedUser().getId());
        userFollowing.removeFollowing(userFollower.getId());
        userFollower.removeFollowers(userFollowing.getId());
        repository.saveAll(Arrays.asList(userFollower, userFollowing));
    }

    public UserProfile getCurrentUser() {
        return repository.findById(UserService.getAuthenticatedUser().getId())
                .orElseThrow(() -> new ObjectNotFoundException("Não há usuário logado"));

    }

    @Transactional
    public UserProfile userUpdate(UserProfile user) {
        UserProfile currentUser = getCurrentUser();
        if( !(user.getId().equals(currentUser.getId())) ) throw new AuthorizationException("Você só pode atualizar o seu próprio perfil");
        currentUser.setImageUrl(user.getImageUrl());
        currentUser.setUsername(user.getUsername());
        return repository.save(currentUser);
    }

    public Page<UserProfileSearchByNameDTO> findByUsername(String username, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findByUsernameContaining(username, pageRequest).map(UserProfileSearchByNameDTO::new);
    }

    public Page<UserProfileSearchPostDTO> search(List<Long> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);
        return repository.findByIdIn(ids, pageRequest).map(UserProfileSearchPostDTO::new);
    }
}
