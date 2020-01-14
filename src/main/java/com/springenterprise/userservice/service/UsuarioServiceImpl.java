package com.springenterprise.userservice.service;

import com.spring.enterprise.common.exceptions.NotFoundException;
import com.spring.enterprise.common.service.AbstractJpaAsyncService;
import com.spring.enterprise.common.service.JpaCrudService;
import com.springenterprise.userservice.domain.User;
import com.springenterprise.userservice.dto.InUserDTO;
import com.springenterprise.userservice.dto.OutUserDTO;
import com.springenterprise.userservice.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Callable;

@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UserMapper mapper;

    @Autowired
    @Qualifier("jdbcScheduler")
    Scheduler jdbcScheduler;

    protected <S> Mono<S> asyncCallable(Callable<S> callable) {
        return Mono.fromCallable(callable).subscribeOn(Schedulers.parallel()).publishOn(jdbcScheduler);
    }

    protected <S> Flux<S> asyncIterable(Iterable<S> iterable) {
        return Flux.fromIterable(iterable).subscribeOn(Schedulers.parallel()).publishOn(jdbcScheduler);
    }

    public Mono<OutUserDTO> create(InUserDTO input) {
        return asyncCallable(() -> mapper.toEntity(input))
                .map(entity -> repository.save(entity))
                .onErrorMap(err -> {
                    log.error("Error occured while creating entity", err);
                    return new Exception("Error occured while creating entity");
                })
                .map(saved -> mapper.toDto(saved));
    }

    public Mono<OutUserDTO> update(UUID uuid, InUserDTO input) {

        return asyncCallable(() -> repository.findByUuid(uuid.toString()))
                .switchIfEmpty(Mono.error(new NotFoundException("Entry not found with uuid " + uuid + ".No update was done.")))
                .onErrorMap(err -> {
                    log.error("Error occured while fetching entity", err);
                    return new Exception("Error occured while fetching entity");
                })
                .map(fetched -> mapper.toEntity(input))
                .map(entity -> repository.save(entity))
                .map(saved -> mapper.toDto(saved));
    }

    public Mono<Void> delete(UUID uuid) {

        return asyncCallable(() -> repository.findByUuid(uuid.toString()))
                .switchIfEmpty(Mono.error(new NotFoundException("Entry not found with UUID " + uuid)))
                .map(Optional::get)
                .map(entity -> {
                    repository.delete(entity);
                    return null;
                });
    }

    @Override
    public Flux<OutUserDTO> findAll() {
        return asyncIterable(() -> mapper.toDto(repository.findAll()).iterator());
    }

    @Override
    public Mono<OutUserDTO> findByUuid(UUID uuid) {
        return asyncCallable(() -> repository.findByUuid(uuid.toString()))
                .switchIfEmpty(Mono.error(new NotFoundException("Entry not found with UUID " + uuid)))
                .onErrorMap(err -> {
                    log.error("Error occured while fetching entity", err);
                    return new Exception("Error occured while fetching entity");
                })
                .map(Optional::get)
                .map(fetched -> mapper.toDto(fetched));
    }
}
