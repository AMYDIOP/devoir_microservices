package com.example.userservice.controller;

import com.example.userservice.model.User;
import com.example.userservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private IUserService userService;

    // 🔹 GET all users
    @GetMapping
    public CollectionModel<EntityModel<User>> getAllUsers() {
        List<EntityModel<User>> users = userService.getAllUsers().stream()
                .map(this::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users,
                linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel());
    }

    // 🔹 GET user by ID
    @GetMapping("/{id}")
    public EntityModel<User> getUserById(@PathVariable Long id) {
        return toModel(userService.getUserById(id));
    }

    // 🔹 POST create
    @PostMapping
    public EntityModel<User> createUser(@RequestBody User user) {
        User saved = userService.createUser(user);
        return toModel(saved);
    }

    // 🔹 PUT update
    @PutMapping("/{id}")
    public EntityModel<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return toModel(userService.updateUser(id, user));
    }

    // 🔹 DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 HATEOAS util
    private EntityModel<User> toModel(User u) {
        return EntityModel.of(u,
                linkTo(methodOn(UserController.class).getUserById(u.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("all-users"),
                linkTo(methodOn(UserController.class).updateUser(u.getId(), u)).withRel("update"),
                linkTo(methodOn(UserController.class).deleteUser(u.getId())).withRel("delete")
        );
    }
}
