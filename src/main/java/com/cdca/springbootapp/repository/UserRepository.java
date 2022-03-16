package com.cdca.springbootapp.repository;

import com.cdca.springbootapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Cdca
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
