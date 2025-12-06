package com.app.ecom;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private List<User> users = new ArrayList<User>();
    private long nextId = 1L;

    public List<User> fetchAllUsers() {
        return users;
    }

    public void addUser(User user) {
        user.setId(nextId++);
        users.add(user);
    }

    public Optional<User> fetchUser(Long id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    public boolean updateUser(Long id, User updatedUser) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .map(us -> {
                    us.setFirstName(updatedUser.getFirstName());
                    us.setLastName(updatedUser.getLastName());
                    return true;
                }).orElse(false);
    }
}
