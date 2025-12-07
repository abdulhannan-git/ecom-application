package com.app.ecom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    //private List<User> users = new ArrayList<User>();
    //private long nextId = 1L;
    @Autowired
    private UserRepository userRepository;

    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        //user.setId(nextId++);
        //users.add(user);
        userRepository.save(user);
    }

    public Optional<User> fetchUser(Long id) {
        //return users.stream().filter(user -> user.getId() == id).findFirst();
        return userRepository.findById(id);
    }

    public boolean updateUser(Long id, User updatedUser) {
        /*return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .map(us -> {
                    us.setFirstName(updatedUser.getFirstName());
                    us.setLastName(updatedUser.getLastName());
                    return true;
                }).orElse(false);*/
        return userRepository.findById(id)
                .map(u -> {
                    u.setFirstName(updatedUser.getFirstName());
                    u.setLastName(updatedUser.getLastName());
                    userRepository.save(u);
                    return true;
                }).orElse(false);
    }
}
