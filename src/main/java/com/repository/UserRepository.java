package com.repository;

import com.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Donnie on 2017/2/28.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
