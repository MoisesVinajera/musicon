package mx.uady.musicon.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import mx.uady.musicon.model.Artist;
import mx.uady.musicon.model.Song;

@Repository
@EnableJpaRepositories
public interface SongRepository extends JpaRepository<Song, Integer>{
    
    Optional<Song> findByName(String name);
    
    List<Song> findByNameContaining(String name);

    List<Song> findByGenre(String name);

    List<Song> findByLocation(String location);

    List<Song> findByArtist(Artist artist);

    void deleteAllByArtistId(Integer id);
}
