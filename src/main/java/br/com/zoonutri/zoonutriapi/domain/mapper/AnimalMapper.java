package br.com.zoonutri.zoonutriapi.domain.mapper;


import br.com.zoonutri.zoonutriapi.domain.Animal;
import br.com.zoonutri.zoonutriapi.domain.dto.AnimalDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { BiometryMapper.class, TaskMapper.class})
public interface AnimalMapper extends AbstractMapper<Animal, AnimalDTO> {

}
