package com.springenterprise.userservice.mapper;

import com.spring.enterprise.common.mapper.EntityMapper;
import com.springenterprise.userservice.domain.User;
import com.springenterprise.userservice.dto.InUserDTO;
import com.springenterprise.userservice.dto.OutUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<User, InUserDTO, OutUserDTO> {

}
