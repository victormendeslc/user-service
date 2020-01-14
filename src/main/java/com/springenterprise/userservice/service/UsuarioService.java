package com.springenterprise.userservice.service;

import com.spring.enterprise.common.service.JpaCrudService;
import com.springenterprise.userservice.domain.User;
import com.springenterprise.userservice.dto.InUserDTO;
import com.springenterprise.userservice.dto.OutUserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService extends JpaCrudService<User, InUserDTO, OutUserDTO> {
}
