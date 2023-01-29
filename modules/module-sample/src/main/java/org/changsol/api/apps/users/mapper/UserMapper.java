package org.changsol.api.apps.users.mapper;

import org.changsol.api.apps.samples.dto.SampleMasterDto;
import org.changsol.api.apps.users.dto.UserDto;
import org.changsol.api.apps.users.domain.Users;
import org.changsol.api.utils.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<Users, UserDto.Response, UserDto.Response, SampleMasterDto.Response> {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}
