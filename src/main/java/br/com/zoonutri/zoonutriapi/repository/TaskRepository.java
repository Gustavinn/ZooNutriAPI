package br.com.zoonutri.zoonutriapi.repository;

import br.com.zoonutri.zoonutriapi.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findAllByAnimalId(final Integer animalId);

    List<Task> findAllByResponsibleUserId(final Integer userId);
}
