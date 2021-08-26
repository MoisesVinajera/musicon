package mx.uady.musicon.repository.relationsRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import mx.uady.musicon.model.relations.PlaylistSongs;

@Repository
@EnableJpaRepositories
public interface PlaylistSongsRepository extends JpaRepository<PlaylistSongs, Integer>{
    
    List<PlaylistSongs> findByOrderSongContaining(Integer orderSong);

    Optional<PlaylistSongs> findByOrderSong(Integer orderSong);

    List<PlaylistSongs> findBySongIdAndPlaylistId(Integer songId, Integer playlistId);

    Optional<PlaylistSongs> findBySongIdAndPlaylistIdAndOrderSong(Integer songId, Integer playlistId, Integer orderSong);
}

