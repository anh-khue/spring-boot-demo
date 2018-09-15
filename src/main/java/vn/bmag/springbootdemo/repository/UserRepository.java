package vn.bmag.springbootdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.bmag.springbootdemo.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
