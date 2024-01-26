package br.com.zoonutri.zoonutriapi.repository;

import br.com.zoonutri.zoonutriapi.domain.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
}
