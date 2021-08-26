package mx.uady.musicon.repository.relationsRepository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import mx.uady.musicon.model.Artist;
import mx.uady.musicon.model.User;
import mx.uady.musicon.model.relations.FollowArtist;

@Repository
@EnableJpaRepositories
public interface FollowArtistRepository extends JpaRepository<FollowArtist, Integer>  {
    
    Optional<FollowArtist> findByUserIdAndArtistId(Integer userId, Integer artistId);

    List<FollowArtist> findByArtist(Artist artist);

    List<FollowArtist> findByUser(User user);

    void deleteAllByUserId(Integer id);
}
