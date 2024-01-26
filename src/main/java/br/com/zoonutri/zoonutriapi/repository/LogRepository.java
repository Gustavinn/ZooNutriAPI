package br.com.zoonutri.zoonutriapi.repository;

import br.com.zoonutri.zoonutriapi.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {

    List<Log> findAllByUserIdOrderByCreationDateDesc(final Integer userId);

    List<Log> findAllByOrderByCreationDateDesc();

}
