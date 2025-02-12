package com.jamongdan.daban.user.repository;

import com.jamongdan.daban.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {

}
