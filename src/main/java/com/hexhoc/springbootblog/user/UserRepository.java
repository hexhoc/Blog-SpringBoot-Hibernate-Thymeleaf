package com.hexhoc.springbootblog.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :passwordMd5")
    User login(@Param("username") String username,
               @Param("passwordMd5") String passwordMd5);

}
