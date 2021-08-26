package mx.uady.musicon.repository.relationsRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import mx.uady.musicon.model.User;
import mx.uady.musicon.model.relations.SongPlay;

@Repository
@EnableJpaRepositories
public interface SongPlayRepository extends JpaRepository<SongPlay, Integer>{
    
    List<SongPlay> findByLocationContaining(String location);

    List<SongPlay> findByUserIdAndSongId(Integer userId, Integer songId);

    List<SongPlay> findByUser(User user);

    void deleteAllByUserId(Integer id);
}
