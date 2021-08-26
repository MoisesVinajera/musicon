package mx.uady.musicon.repository.relationsRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import mx.uady.musicon.model.User;
import mx.uady.musicon.model.relations.PodcastPlay;

@Repository
@EnableJpaRepositories
public interface PodcastPlayRepository extends JpaRepository<PodcastPlay, Integer>{
    
    List<PodcastPlay> findByLocationContaining(String location);

    List<PodcastPlay> findByUserIdAndPodcastId(Integer userId, Integer podcastId);

    List<PodcastPlay> findByUser(User user);

    void deleteAllByUserId(Integer id);
}
