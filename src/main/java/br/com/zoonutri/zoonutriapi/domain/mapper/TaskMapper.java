package br.com.zoonutri.zoonutriapi.domain.mapper;

import br.com.zoonutri.zoonutriapi.domain.Task;
import br.com.zoonutri.zoonutriapi.domain.dto.TaskDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {AnimalMapper.class, UserMapper.class})
public interface TaskMapper extends AbstractMapper<Task, TaskDTO> {

    @Mappings({
            @Mapping(source = "responsibleUserId", target = "responsibleUser.id"),
            @Mapping(source = "responsibleUserName", target = "responsibleUser.name"),
            @Mapping(source = "animalId", target = "animal.id")
    })
    @Override
    Task mapToEntity(TaskDTO dto);
}
