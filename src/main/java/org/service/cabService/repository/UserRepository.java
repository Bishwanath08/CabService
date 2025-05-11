package org.service.cabService.repository;


import org.service.cabService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {
       User findByMobile(String mobile);
       User findByToken(String token);
}
