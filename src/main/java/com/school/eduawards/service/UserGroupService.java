package com.school.eduawards.service;

import com.school.eduawards.entity.UserGroup;
import com.school.eduawards.repository.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGroupService {
    @Autowired
    private UserGroupRepository repository;

    public List<UserGroup> getAll() {
        return repository.findAll();
    }

    public UserGroup save(UserGroup userGroup) {
        return repository.save(userGroup);
    }

    public UserGroup getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
