package br.com.zoonutri.zoonutriapi.domain.mapper;

import br.com.zoonutri.zoonutriapi.domain.User;
import br.com.zoonutri.zoonutriapi.domain.dto.UserWithPasswordDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { TaskMapper.class, UserRoleMapper.class })
public interface UserWithPasswordMapper extends AbstractMapper<User, UserWithPasswordDTO> {
}
