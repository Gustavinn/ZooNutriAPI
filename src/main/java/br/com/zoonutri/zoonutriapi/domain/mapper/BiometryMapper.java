package br.com.zoonutri.zoonutriapi.domain.mapper;

import br.com.zoonutri.zoonutriapi.domain.Biometry;
import br.com.zoonutri.zoonutriapi.domain.dto.BiometryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AnimalMapper.class})
public interface BiometryMapper extends AbstractMapper<Biometry, BiometryDTO> {

    @Mapping(source = "animalId", target = "animal.id")
    @Mapping(source = "animalName", target = "animal.nickname")
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "userName", target = "user.name")
    Biometry toEntity(BiometryDTO dto);
}
