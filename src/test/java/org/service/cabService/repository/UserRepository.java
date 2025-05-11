package org.cabService.cabService.repository;

import org.cabService.cabService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
       User findByMobile(String mobile);
       User findByToken(String token);
}
