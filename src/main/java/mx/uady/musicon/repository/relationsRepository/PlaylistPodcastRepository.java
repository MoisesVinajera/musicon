package mx.uady.musicon.repository.relationsRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import mx.uady.musicon.model.relations.PlaylistPodcast;

@Repository
@EnableJpaRepositories
public interface PlaylistPodcastRepository extends JpaRepository<PlaylistPodcast, Integer>{
    
}
