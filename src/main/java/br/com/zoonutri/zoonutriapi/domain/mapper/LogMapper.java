package br.com.zoonutri.zoonutriapi.domain.mapper;

import br.com.zoonutri.zoonutriapi.domain.Log;
import br.com.zoonutri.zoonutriapi.domain.dto.LogDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface LogMapper extends AbstractMapper<Log, LogDTO> {

    @Mapping(source = "user.name", target = "userName")
    LogDTO mapToDto(Log entity);
}
