package br.com.zoonutri.zoonutriapi.service;

import br.com.zoonutri.zoonutriapi.domain.Animal;
import br.com.zoonutri.zoonutriapi.domain.dto.AnimalDTO;
import br.com.zoonutri.zoonutriapi.domain.mapper.AnimalMapper;
import br.com.zoonutri.zoonutriapi.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static br.com.zoonutri.zoonutriapi.service.LogService.ANIMAL_ICON;
import static br.com.zoonutri.zoonutriapi.util.GeneralUtil.getMessage;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class AnimalService {
    public static final String MSG_ERROR_ANIMAL_ID = "msg.error.animal.id";

    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;
    private final LogService logService;

    public Animal findById(final Integer animalId) {
        return animalRepository.findById(animalId)
                .orElseThrow(() -> new IllegalArgumentException(MSG_ERROR_ANIMAL_ID + animalId)
        );
    }

    public List<AnimalDTO> findAnimals() {
        return animalMapper.toDtoList(animalRepository.findAll());
    }

    public void saveAnimal(final AnimalDTO animalDTO) {
        final Animal animal = animalMapper.mapToEntity(animalDTO);

        if (isNull(animal.getCreationDate())) {
            animal.setCreationDate(new Date());
        }

        animalRepository.save(animal);
        logService.saveLog(logService.createLogDTO(ANIMAL_ICON, "inseriu um animal às"));
    }

    public void deleteAnimal(final Integer animalId) {
        animalRepository.delete(animalRepository.findById(animalId)
                .orElseThrow(() -> new IllegalArgumentException(
                        getMessage(MSG_ERROR_ANIMAL_ID) + animalId)));
        logService.saveLog(logService.createLogDTO(ANIMAL_ICON, "removeu um animal às"));
    }

}
