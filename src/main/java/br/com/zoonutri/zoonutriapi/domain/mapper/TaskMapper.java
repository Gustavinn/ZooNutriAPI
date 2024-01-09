package br.com.zoonutri.zoonutriapi.domain.mapper;

import br.com.zoonutri.zoonutriapi.domain.Task;
import br.com.zoonutri.zoonutriapi.domain.dto.TaskDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AnimalMapper.class, UserMapper.class})
public interface TaskMapper extends AbstractMapper<Task, TaskDTO> {

    @Mapping(source = "responsibleUserId", target = "responsibleUser.id")
    @Mapping(source = "responsibleUserName", target = "responsibleUser.name")
    @Mapping(source = "animalId", target = "animal.id")
    Task toEntity(TaskDTO dto);
}
