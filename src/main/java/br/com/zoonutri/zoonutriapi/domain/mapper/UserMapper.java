package br.com.zoonutri.zoonutriapi.domain.mapper;

import br.com.zoonutri.zoonutriapi.domain.User;
import br.com.zoonutri.zoonutriapi.domain.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TaskMapper.class, UserRoleMapper.class})
public interface UserMapper extends AbstractMapper<User, UserDTO> {

}
