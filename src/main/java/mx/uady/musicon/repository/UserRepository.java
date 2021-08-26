package mx.uady.musicon.repository;

import java.util.Optional;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import mx.uady.musicon.model.User;

@Repository
@EnableJpaRepositories
@EntityScan(basePackages = {"mx.uady.musicon.model.User"})
public interface UserRepository extends JpaRepository<User, Integer>{
    
    Optional<User> findByMail(String mail);

    Optional<User> findByName(String name);
}
