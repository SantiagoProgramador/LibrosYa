package com.riwi.LibrosYa.infrastructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.riwi.LibrosYa.api.dto.request.UserRequest;
import com.riwi.LibrosYa.api.dto.response.UserResponse;
import com.riwi.LibrosYa.domain.model.User;
import com.riwi.LibrosYa.infrastructure.persistence.UserEntity;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public UserResponse userToUserResponse(User user);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "loans",ignore = true)
    @Mapping(target = "reservations",ignore = true)
    public User userRequestToUser(UserRequest userRequest);

    public UserEntity userToUserEntity(User user);

    public User userEntityToUser(UserEntity userEntity);
}
