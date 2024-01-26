package br.com.zoonutri.zoonutriapi.repository;

import br.com.zoonutri.zoonutriapi.domain.Biometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiometryRepository extends JpaRepository<Biometry, Integer> {

    List<Biometry> findBiometricsByAnimalId(final Integer animalId);
}
