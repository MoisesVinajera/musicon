package mx.uady.musicon.service.relationsService;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import mx.uady.musicon.exception.NotFoundException;
import mx.uady.musicon.model.Song;
import mx.uady.musicon.model.User;
import mx.uady.musicon.model.relations.SongPlay;
import mx.uady.musicon.model.request.SongPlayRequest;
import mx.uady.musicon.repository.SongRepository;
import mx.uady.musicon.repository.UserRepository;
import mx.uady.musicon.repository.relationsRepository.SongPlayRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongPlayService {
    
    @Autowired
    private SongPlayRepository songPlayRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SongRepository songRepository;

    
    @Transactional(readOnly = true)
    public List<SongPlay> getSongPlays(){
        List<SongPlay> songPlays = new LinkedList<>();

        songPlayRepository.findAll().iterator().forEachRemaining(songPlays::add); 
        
        return songPlays;    
    }

    public List<SongPlay> searchSongPlayByLocation(String location) {
        return songPlayRepository.findByLocationContaining(location);
    }

    @Transactional(readOnly = true)
    public SongPlay getSongPlay(Integer id) {

        Optional<SongPlay> opt = songPlayRepository.findById(id);

        if (!opt.isPresent()) {
            throw new NotFoundException();
        }

        return opt.get();
    }

    @Transactional
    public SongPlay createSongPlay(Integer userId, Integer songId){
        
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Song> songOptional = songRepository.findById(songId);
        if (!userOptional.isPresent() || !songOptional.isPresent()) {
            throw new NotFoundException();
        }

        
        //Actualizar reproducciones
        songOptional.get().setReproductions(songOptional.get().getReproductions()+1);
        songRepository.save(songOptional.get());

        SongPlay songPlayNew = new SongPlay();
        
        songPlayNew.setUser(userOptional.get());
        songPlayNew.setSong(songOptional.get());
        songPlayNew.setLocation(userOptional.get().getLocation());

        songPlayRepository.save(songPlayNew);

        return songPlayNew;
    }

    @Transactional
    public 
    List<SongPlay> editSongPlay(Integer userId, Integer songId,SongPlayRequest request) {

        List<SongPlay> songPlayEditOptional = songPlayRepository
                                                .findByUserIdAndSongId(userId,songId);
 
        if (songPlayEditOptional == null) {
            throw new NotFoundException();
        }

        for (SongPlay songPlay : songPlayEditOptional) {
            songPlay.setLocation(request.getLocation());
            songPlayRepository.save(songPlay);
        }
 
        return songPlayEditOptional;
    }

    @Transactional
    public Void deleteSongPlay(Integer id) {

        Optional<SongPlay> Optional = songPlayRepository.findById(id);
        
        if(!Optional.isPresent()){
            throw new NotFoundException();
        }
        songPlayRepository.delete(Optional.get()); 
        return null;
        
    }
}
