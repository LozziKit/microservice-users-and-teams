package io.lozzikit.users.utils;

import io.lozzikit.users.api.model.NewUser;
import io.lozzikit.users.api.model.User;
import io.lozzikit.users.entities.UserEntity;
import org.modelmapper.ModelMapper;

public class DaoDtoConverter {

    private ModelMapper modelMapper;

    public DaoDtoConverter() {
        modelMapper = new ModelMapper();
    }

    public UserEntity toUserEntity(NewUser user) {
        return modelMapper.map(user, UserEntity.class);
    }

    public User toUser(UserEntity entity) {
        return modelMapper.map(entity, User.class);
    }
}
