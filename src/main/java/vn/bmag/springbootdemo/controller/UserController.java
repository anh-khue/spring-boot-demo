package vn.bmag.springbootdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.bmag.springbootdemo.model.User;
import vn.bmag.springbootdemo.service.UserService;

import static vn.bmag.springbootdemo.exception.UserExceptions.UsernameExistedException;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(userService.getAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(userService.getById(Integer.parseInt(id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .build();
        }
    }

    @PostMapping("/users")
    public ResponseEntity create(@RequestBody User user) {
        try {
            userService.create(user);
            return ResponseEntity.status(HttpStatus.OK)
                                 .build();
        } catch (UsernameExistedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                                 .build();
        }
    }
}
