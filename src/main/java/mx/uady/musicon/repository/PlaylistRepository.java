package mx.uady.musicon.repository;

import java.util.Optional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import mx.uady.musicon.model.Playlist;
import mx.uady.musicon.model.User;

@Repository
@EnableJpaRepositories
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
    
    Optional<Playlist> findByName(String name);

    List<Playlist> findByNameContaining(String name);

    List<Playlist> findByLocation(String location);

    List<Playlist> findByUser(User user);

    void deleteAllByUserId(Integer id);
}
