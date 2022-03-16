package com.cdca.springbootapp.controller;

import com.cdca.springbootapp.entity.User;
import com.cdca.springbootapp.service.UserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Cdca
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create a new user
    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    // Read an user
    @GetMapping("/{id}")
    // public ResponseEntity<?> read(@PathVariable Long id) { // Si tiene el mismo nombre se puede omitir value
    public ResponseEntity<?> read(@PathVariable(value = "id") Long userId) {
        Optional<User> oUser = userService.findById(userId);
        if (oUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oUser);
    }

        // Update an user
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Long id) {
        Optional<User> oUser = userService.findById(id);
        if (oUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        // BeanUtils.copyProperties(user, oUser.get()); // No se usa porque no queremos copiar id
        oUser.get().setName(user.getName());
        oUser.get().setSurname(user.getSurname());
        oUser.get().setEmail(user.getEmail());
        oUser.get().setEnabled(user.getEnabled());

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(oUser.get()));
    }

    // Delete an user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (userService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
    // Read all users
    @GetMapping
    public List<User> readAll() {
        List<User> users = StreamSupport
                .stream(userService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return users;
    }

}
