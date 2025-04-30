package com.school.eduawards.service;

import com.school.eduawards.entity.User;
import com.school.eduawards.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> getAll() {
        return repository.findAll();
    }

    public User save(User user) {
        return repository.save(user);
    }

    public User getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public User findByUserNameAndPassword(String userName, String password) {
        return repository.findByUserNameAndPassword(userName, password);
    }
}
