package br.com.zoonutri.zoonutriapi.domain.mapper;

import br.com.zoonutri.zoonutriapi.domain.Biometry;
import br.com.zoonutri.zoonutriapi.domain.dto.BiometryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {AnimalMapper.class})
public interface BiometryMapper extends AbstractMapper<Biometry, BiometryDTO> {

    @Mappings({
            @Mapping(source = "animalId", target = "animal.id"),
            @Mapping(source = "animalName", target = "animal.nickname"),
            @Mapping(source = "userId", target = "user.id"),
            @Mapping(source = "userName", target = "user.name")
    })
    @Override
    Biometry mapToEntity(BiometryDTO dto);
}
