package mx.uady.musicon.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import mx.uady.musicon.model.Album;

@Repository
@EnableJpaRepositories
public interface AlbumRepository extends JpaRepository<Album, Integer>{
    
    Optional<Album> findByName(String name);

    List<Album> findByNameContaining(String name);

}
