package com.springenterprise.userservice.controller;

import com.spring.enterprise.common.controller.AbstractCrudController;
import com.springenterprise.userservice.dto.InUserDTO;
import com.springenterprise.userservice.dto.OutUserDTO;
import com.springenterprise.userservice.service.UsuarioService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController extends AbstractCrudController<InUserDTO, OutUserDTO, UsuarioService> {

    public UserController(UsuarioService service) {
        super(service);
    }
}