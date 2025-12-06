package com.app.ecom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.fetchAllUsers());
    }

    @PostMapping("/api/users")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>("user added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userService.fetchUser(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) {
        boolean updated = userService.updateUser(id, user);
        if (updated)
            return new ResponseEntity<>("user updated successfully", HttpStatus.OK);
        return new ResponseEntity<>("user update failed", HttpStatus.BAD_REQUEST);
    }

}
