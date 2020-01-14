package com.springenterprise.userservice.repository;


import com.spring.enterprise.common.repository.JpaCrudRepository;
import com.springenterprise.userservice.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaCrudRepository<User> {


}
