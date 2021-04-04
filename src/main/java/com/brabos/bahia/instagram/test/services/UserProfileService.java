package com.brabos.bahia.instagram.test.services;

import com.brabos.bahia.instagram.test.domains.UserProfile;
import com.brabos.bahia.instagram.test.dto.NewUserProfileDTO;
import com.brabos.bahia.instagram.test.repositories.UserProfileRepository;
import com.brabos.bahia.instagram.test.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository repository;

    @Autowired
    private BCryptPasswordEncoder pe;

    public UserProfile findById(Long id){
        Optional<UserProfile> userProfile = repository.findById(id);
        return userProfile.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado para o id: " + id));
    }


    public List<UserProfile> findAll(){
        List<UserProfile> userProfiles = repository.findAll();
        if(userProfiles.size() == 0){
            throw new ObjectNotFoundException("Nenhum usuário cadastrado");
        }
        return userProfiles;
    }

    public Page<UserProfile> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
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
}
