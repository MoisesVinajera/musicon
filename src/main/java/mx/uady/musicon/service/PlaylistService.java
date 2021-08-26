package mx.uady.musicon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import mx.uady.musicon.exception.NotFoundException;
import mx.uady.musicon.model.Playlist;
import mx.uady.musicon.model.User;
import mx.uady.musicon.model.request.PlaylistRequest;
import mx.uady.musicon.repository.PlaylistRepository;
import mx.uady.musicon.repository.UserRepository;

@Service
public class PlaylistService {
    
    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private UserRepository userRepository;

    private MailService mailService = new MailService();

    @Transactional(readOnly = true)
    public List<Playlist> getPlaylists(){
        List<Playlist> playlists = new LinkedList<>();

        playlistRepository.findAll().iterator().forEachRemaining(playlists::add); // SELECT(id, nombre)
        
        return playlists;    
    }

    public List<Playlist> searchPlaylistByLocation(String name) {
        return playlistRepository.findByLocation(name);
    }

    @Transactional(readOnly = true)
    public Playlist getPlaylist(Integer id) {

        Optional<Playlist> opt = playlistRepository.findById(id);

        if (!opt.isPresent()) {
            throw new NotFoundException();
        }

        return opt.get();
    }

    @Transactional
    public Playlist createPlaylist(PlaylistRequest request){
        
        Optional<User> userOptional = userRepository.findById(request.getUserId());
        
        if (!userOptional.isPresent()) {
            throw new NotFoundException();
        }
        
        Playlist playlistNew = new Playlist();

        playlistNew.setName(request.getName());
        playlistNew.setUser(userOptional.get());
        playlistNew.setDuration(request.getDuration());
        playlistNew.setLocation(request.getLocation());
        playlistNew.setUrlCover(request.getUrlCover());
        playlistNew.setGenre(request.getGenre());
        playlistNew.setRating(request.getRating());

        playlistRepository.save(playlistNew);

        return playlistNew;
    }

    @Transactional
    public Playlist editPlaylist(Integer id, PlaylistRequest request) {

        Optional<Playlist> playlistEditOptional = playlistRepository.findById(id);

        if (!playlistEditOptional.isPresent()) {
            throw new NotFoundException();
        }

        Optional<User> userOptional = userRepository.findById(request.getUserId());
        
        if (!userOptional.isPresent()) {
            throw new NotFoundException();
        }
        
        playlistEditOptional.get().setName(request.getName());
        playlistEditOptional.get().setUser(userOptional.get());
        playlistEditOptional.get().setDuration(request.getDuration());
        playlistEditOptional.get().setLocation(request.getLocation());
        playlistEditOptional.get().setUrlCover(request.getUrlCover());
        playlistEditOptional.get().setGenre(request.getGenre());
        playlistEditOptional.get().setRating(request.getRating());
        
        playlistRepository.save(playlistEditOptional.get());

        return playlistEditOptional.get();  
    }

    @Transactional
    public Void deletePlaylist(Integer id) throws InterruptedException {

        Optional<Playlist> playlistOptional = playlistRepository.findById(id);
        
        if(!playlistOptional.isPresent()){
            throw new NotFoundException();
        }
        playlistRepository.delete(playlistOptional.get()); 

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String date = LocalDateTime.now().toString();
        String subject = "Playlist removed ";
        String text = "La playlist a las " + date + " con nombre " + playlistOptional.get().getName() + " se ha elimando";

        mailService.sendEmail(playlistOptional.get().getUser().getMail(), playlistOptional.get().getUser().getPassword(), subject, text);

        return null;
        
    }
    
}
