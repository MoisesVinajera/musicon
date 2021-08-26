package mx.uady.musicon.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import mx.uady.musicon.model.Artist;

@Repository
@EnableJpaRepositories
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    
    Optional<Artist> findByName(String name);

    Optional<Artist> findByUserId(Integer userId);

    List<Artist> findByNameContaining(String name);


}
