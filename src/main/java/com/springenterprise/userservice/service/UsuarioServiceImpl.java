package com.springenterprise.userservice.service;

import com.spring.enterprise.common.service.JpaCrudService;
import com.springenterprise.userservice.domain.User;
import com.springenterprise.userservice.dto.UserDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class UsuarioServiceImpl implements JpaCrudService<User, UserDTO, UserDTO> {


    @Override
    public Mono<UserDTO> create(UserDTO dto) {
        return null;
    }

    @Override
    public Mono<UserDTO> update(UUID uuid, UserDTO dto) {
        return null;
    }

    @Override
    public Mono<Void> delete(UUID uuid) {
        return null;
    }

    @Override
    public Flux<UserDTO> findAll() {
        return null;
    }

    @Override
    public Mono<UserDTO> findByUuid(UUID uuid) {
        return null;
    }
}
