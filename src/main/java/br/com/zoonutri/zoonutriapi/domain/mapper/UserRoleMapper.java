package br.com.zoonutri.zoonutriapi.domain.mapper;

import br.com.zoonutri.zoonutriapi.domain.UserRole;
import br.com.zoonutri.zoonutriapi.domain.dto.UserRoleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRoleMapper extends AbstractMapper<UserRole, UserRoleDTO> {
}
