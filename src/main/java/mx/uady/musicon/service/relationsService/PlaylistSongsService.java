package mx.uady.musicon.service.relationsService;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.uady.musicon.exception.EntityExistsException;
import mx.uady.musicon.exception.NotFoundException;
import mx.uady.musicon.model.Playlist;
import mx.uady.musicon.model.Song;
import mx.uady.musicon.model.relations.PlaylistSongs;
import mx.uady.musicon.model.request.PlaylistSongsRequest;
import mx.uady.musicon.repository.PlaylistRepository;
import mx.uady.musicon.repository.SongRepository;
import mx.uady.musicon.repository.relationsRepository.PlaylistSongsRepository;

@Service
public class PlaylistSongsService {
    
    @Autowired
    private PlaylistSongsRepository playlistSongsRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Transactional(readOnly = true)
    public List<PlaylistSongs> getPlaylistSongs(){
        List<PlaylistSongs> playlistSongs = new LinkedList<>();

        playlistSongsRepository.findAll().iterator().forEachRemaining(playlistSongs::add); 
        
        return playlistSongs;    
    }

    public List<PlaylistSongs> searchPlaylistSongByOrder(Integer order) {
        return playlistSongsRepository.findByOrderSongContaining(order);
    }

    @Transactional(readOnly = true)
    public PlaylistSongs getPlaylistSong(Integer id) {

        Optional<PlaylistSongs> opt = playlistSongsRepository.findById(id);

        if (!opt.isPresent()) {
            throw new NotFoundException();
        }

        return opt.get();
    }

    @Transactional
    public PlaylistSongs createPlaylistSong(PlaylistSongsRequest request){
        
        Optional<Song> songOptional = songRepository.findById(request.getSongId());
        Optional<Playlist> playlistOptional = playlistRepository.findById(request.getPlaylistId());
        if (!songOptional.isPresent() || !playlistOptional.isPresent()) {
            throw new NotFoundException();
        }

        Optional<PlaylistSongs> playlistSongOptional = playlistSongsRepository
                                                    .findBySongIdAndPlaylistIdAndOrderSong(request.getSongId(),request.getPlaylistId(),request.getOrderSong());
        if (playlistSongOptional.isPresent() ) {
            throw new EntityExistsException();
        }
        PlaylistSongs playlistSong = new PlaylistSongs();
        
        playlistSong.setOrderSong(request.getOrderSong());
        playlistSong.setPlaylist(playlistOptional.get());
        playlistSong.setSong(songOptional.get());

        playlistSongsRepository.save(playlistSong);

        return playlistSong;
    }

    @Transactional
    public PlaylistSongs editPlaylistSong(PlaylistSongsRequest request) {

        Optional<PlaylistSongs> playlistSongOptional = playlistSongsRepository
                                                    .findBySongIdAndPlaylistIdAndOrderSong(request.getSongId(),request.getPlaylistId(),request.getOrderSong());
        if (playlistSongOptional == null) {
            throw new NotFoundException();
        }
        return playlistSongOptional.get();
    }

    @Transactional
    public Void deletePlaylistSong(Integer id) {

        Optional<PlaylistSongs> Optional = playlistSongsRepository.findById(id);
        
        if(!Optional.isPresent()){
            throw new NotFoundException();
        }
        playlistSongsRepository.delete(Optional.get()); 
        return null;
        
    }
}
