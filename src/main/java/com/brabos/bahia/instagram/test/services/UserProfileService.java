package com.brabos.bahia.instagram.test.services;

import com.brabos.bahia.instagram.test.domains.UserProfile;
import com.brabos.bahia.instagram.test.repositories.UserProfileRepository;
import com.brabos.bahia.instagram.test.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfile find(Long id){
        Optional<UserProfile> userProfile = userProfileRepository.findById(id);
        return userProfile.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado para o id: " + id));
    }

    public List<UserProfile> findAll(){
        List<UserProfile> userProfiles = userProfileRepository.findAll();
        if(userProfiles.size() == 0){
            throw new ObjectNotFoundException("Nenhum usuário cadastrado");
        }
        return userProfiles;
    }

    public Page<UserProfile> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return userProfileRepository.findAll(pageRequest);
    }
}
