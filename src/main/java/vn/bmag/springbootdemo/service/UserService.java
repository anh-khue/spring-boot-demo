package vn.bmag.springbootdemo.service;

import org.springframework.stereotype.Service;
import vn.bmag.springbootdemo.exception.UserExceptions;
import vn.bmag.springbootdemo.model.User;
import vn.bmag.springbootdemo.repository.RoleRepository;
import vn.bmag.springbootdemo.repository.UserRepository;

import java.util.List;

import static vn.bmag.springbootdemo.exception.UserExceptions.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        users.forEach(user -> user.setRole(roleRepository.findById(user.getRoleId())));
        return users;
    }

    public User getById(int id) throws UserNotFoundException {
        return userRepository.findById(id)
                             .orElseThrow(UserNotFoundException::new);
    }

    public void create(User user) throws UsernameExistedException {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameExistedException();
        }

        userRepository.save(user);
    }
}
