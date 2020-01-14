package com.springenterprise.userservice.service;


import com.springenterprise.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<User, Long> {

    Optional<User> findByUuid(String uuid);

}
