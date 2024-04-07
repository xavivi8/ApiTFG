package com.application.lol.build.ApiUsuario.repository;

import com.application.lol.build.ApiUsuario.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1 AND u.pass = ?2")
    User findByUserAndPass(String email, String pass);
}
