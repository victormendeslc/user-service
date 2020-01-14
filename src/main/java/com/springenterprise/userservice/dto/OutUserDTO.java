package com.springenterprise.userservice.dto;

import com.spring.enterprise.common.dto.DTO;
import lombok.Data;

@Data
public class OutUserDTO implements DTO {

    private String uuid;
    private String username;
    private String activationKey;
}
