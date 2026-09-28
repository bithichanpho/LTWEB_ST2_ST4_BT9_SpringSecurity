package com.webprogramming.mapper;

import com.webprogramming.dto.UserDTO;
import com.webprogramming.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roleName", source = "role.name")
    UserDTO toDTO(User user);
}
