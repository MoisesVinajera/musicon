package mx.uady.musicon.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import mx.uady.musicon.model.Artist;
import mx.uady.musicon.model.Podcast;

@Repository
@EnableJpaRepositories
public interface PodcastRepository extends JpaRepository<Podcast, Integer>{
    
    Optional<Podcast> findByName(String name);

    List<Podcast> findByGenre(String genre);

    List<Podcast> findByLocation(String location);
    
    List<Podcast> findByArtist(Artist artist);
    
    List<Podcast> findByNameContaining(String name);
}
