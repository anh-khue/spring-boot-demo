package vn.bmag.springbootdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.bmag.springbootdemo.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findById(int id);
}
