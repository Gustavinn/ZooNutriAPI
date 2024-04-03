package br.com.zoonutri.zoonutriapi.service;

import br.com.zoonutri.zoonutriapi.domain.Animal;
import br.com.zoonutri.zoonutriapi.domain.Biometry;
import br.com.zoonutri.zoonutriapi.domain.dto.BiometryDTO;
import br.com.zoonutri.zoonutriapi.domain.mapper.BiometryMapper;
import br.com.zoonutri.zoonutriapi.repository.BiometryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.com.zoonutri.zoonutriapi.service.LogService.BIOMETRY_ICON;
import static br.com.zoonutri.zoonutriapi.util.GeneralUtil.getMessage;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class BiometryService {

    @Value("${biometrics.report.path}")
    private String BIOMETRY_REPORT_FILEPATH;

    @Value("${biometrics.report.file.name}")
    private String BIOMETRY_REPORT_FILENAME;

    public static final String MSG_ERROR_BIOMETRY_ID = "msg.error.biometry.id";

    private final BiometryRepository biometryRepository;
    private BiometryMapper biometryMapper;
    private ReportService reportService;
    private final AnimalService animalService;
    private LogService logService;

    public BiometryDTO findBiometryById(final Integer biometryId) {
        return biometryMapper.mapToDto(
                biometryRepository.findById(biometryId)
                        .orElseThrow(() -> new IllegalArgumentException(
                                getMessage(MSG_ERROR_BIOMETRY_ID) + biometryId
                        )));
    }

    public List<BiometryDTO> findAnimalBiometrics(final Integer animalId) {
        return biometryMapper.toDtoList(biometryRepository.findBiometricsByAnimalId(animalId));
    }

    public List<BiometryDTO> findAllBiometrics() {
        return biometryMapper.toDtoList(biometryRepository.findAll());
    }

    public void saveBiometry(final BiometryDTO biometryDTO, Boolean isUpdate) {
        final Biometry biometry = biometryMapper.mapToEntity(biometryDTO);

        if (isNull(biometry.getCreationDate())) {
            biometry.setCreationDate(new Date());
        }

        biometryRepository.save(biometry);

        if (isUpdate) {
            logService.saveLog(logService.createLogDTO(BIOMETRY_ICON, "atualizou uma biometria às"));
        } else {
            logService.saveLog(logService.createLogDTO(BIOMETRY_ICON, "inseriu uma biometria às"));
        }
    }

    public void deleteBiometry(final Integer biometryId) {
        biometryRepository.delete(biometryRepository.findById(biometryId)
                .orElseThrow(() -> new IllegalArgumentException(
                        getMessage(MSG_ERROR_BIOMETRY_ID) + biometryId)
                ));

        logService.saveLog(logService.createLogDTO(BIOMETRY_ICON, "removeu uma biometria às"));
    }

    public void generatePdfReport(final Integer biometryId, final HttpServletResponse response) {

        final Biometry biometry = biometryRepository.findById(biometryId)
                .orElseThrow(() -> new IllegalArgumentException(
                        getMessage(MSG_ERROR_BIOMETRY_ID) + biometryId
                ));

        final Animal animal = biometry.getAnimal();
        final Map<String, Object> parameters = new HashMap<>();

        parameters.put("nickname", animal.getNickname());
        parameters.put("popular_name", animal.getPopularName());
        parameters.put("scientific_name", animal.getScientificName());
        parameters.put("responsible", biometry.getUser().getName());
        parameters.put("biometry_date", biometry.getCreationDate().toString());
        parameters.put("note", biometry.getNote());
        parameters.put("prescription", biometry.getPrescription());
        parameters.put("weight", biometry.getWeight());
        parameters.put("height", biometry.getHeight());
        parameters.put("length", biometry.getLength());

        reportService.exportToPdfStream(BIOMETRY_REPORT_FILEPATH, BIOMETRY_REPORT_FILENAME, parameters, response);
        logService.saveLog(logService.createLogDTO(BIOMETRY_ICON, "gerou relatório às"));
    }

}
