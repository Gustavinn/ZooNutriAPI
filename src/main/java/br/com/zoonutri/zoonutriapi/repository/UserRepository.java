package br.com.zoonutri.zoonutriapi.repository;

import br.com.zoonutri.zoonutriapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(final String email);

    long countByEmail(final String email);

    Optional<User> findByEmailAndPassword(final String email, final String password);

    Optional<User> findByEmailAndHash(final String email, final String hashUser);

}
