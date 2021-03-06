package com.dispnt.mall.repository;

import com.dispnt.mall.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByJsonWebToken(String jsonWebToken);

    Boolean existsByStuId(Integer stuId);

    List<User> findBystuId(Integer stuId);

}
