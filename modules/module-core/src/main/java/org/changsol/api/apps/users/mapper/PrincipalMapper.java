package org.changsol.api.apps.users.mapper;

import org.changsol.api.apps.users.domain.Users;
import org.changsol.api.apps.users.dto.PrincipalDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PrincipalMapper {
    PrincipalMapper INSTANCE = Mappers.getMapper(PrincipalMapper.class);

    PrincipalDto.Response toPrincipal(Users user);
}
