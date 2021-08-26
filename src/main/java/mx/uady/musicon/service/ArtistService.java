package mx.uady.musicon.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uady.musicon.exception.ArtistNotFoundException;
import mx.uady.musicon.exception.NotFoundException;
import mx.uady.musicon.exception.UserInExistanceException;
import mx.uady.musicon.model.Artist;
import mx.uady.musicon.model.Playlist;
import mx.uady.musicon.model.Podcast;
import mx.uady.musicon.model.Song;
import mx.uady.musicon.model.User;
import mx.uady.musicon.model.relations.FollowArtist;
import mx.uady.musicon.model.request.ArtistRequest;
import mx.uady.musicon.repository.ArtistRepository;
import mx.uady.musicon.repository.PlaylistRepository;
import mx.uady.musicon.repository.PodcastRepository;
import mx.uady.musicon.repository.SongRepository;
import mx.uady.musicon.repository.UserRepository;
import mx.uady.musicon.repository.relationsRepository.FollowArtistRepository;
import mx.uady.musicon.repository.relationsRepository.PodcastPlayRepository;
import mx.uady.musicon.repository.relationsRepository.SongPlayRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private SongPlayRepository songPlayRepository;

    @Autowired 
    private PodcastPlayRepository podcastPlayRepository;

    @Autowired
    private FollowArtistRepository followArtistRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private PodcastRepository podcastRepository;

    private MailService mailService = new MailService();

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional(readOnly = true)
    public List<Artist> getArtists(){
        List<Artist> artists = new LinkedList<>();

        artistRepository.findAll().iterator().forEachRemaining(artists::add); 
        
        return artists;    
    }

    public List<Artist> searchArtistByName(String name) {
        return artistRepository.findByNameContaining(name);
    }

    @Transactional(readOnly = true)
    public List<Song> getSongsByArtist(Integer id) {

        Optional<Artist> artistOpt = artistRepository.findById(id);

        if (!artistOpt.isPresent()) {
            throw new ArtistNotFoundException();
        }

        List<Song> songsList = songRepository.findByArtist(artistOpt.get());

        if (songsList.isEmpty()) {
            throw new NotFoundException();
        }

        return songsList;
    }

    @Transactional(readOnly = true)
    public List<Podcast> getPodcastsByArtist(Integer id) {

        Optional<Artist> artistOpt = artistRepository.findById(id);

        if (!artistOpt.isPresent()) {
            throw new ArtistNotFoundException();
        }

        List<Podcast> podcastList = podcastRepository.findByArtist(artistOpt.get());

        if (podcastList.isEmpty()) {
            throw new NotFoundException();
        }

        return podcastList;
    }

    @Transactional(readOnly = true)
    public List<Playlist> getPlaylistsByArtist(Integer id) {

        Optional<Artist> artistOpt = artistRepository.findById(id);

        if (!artistOpt.isPresent()) {
            throw new ArtistNotFoundException();
        }

        List<Playlist> playlistList = playlistRepository.findByUser(artistOpt.get().getUser());

        if (playlistList.isEmpty()) {
            throw new NotFoundException();
        }

        return playlistList;
    }

    @Transactional(readOnly = true)
    public List<User> getFollowersByArtist(Integer id) {

        Optional<Artist> artistOpt = artistRepository.findById(id);

        if (!artistOpt.isPresent()) {
            throw new ArtistNotFoundException();
        }

        List<FollowArtist> followerArtistList = followArtistRepository.findByArtist(artistOpt.get());

        if (followerArtistList.isEmpty()) {
            throw new NotFoundException();
        }

        List<User> users = new LinkedList<>();

        for (FollowArtist followArtist : followerArtistList) {
            users.add(followArtist.getUser());
        }
        
        return users;
    }

    @Transactional(readOnly = true)
    public Artist getArtist(Integer id) {

        Optional<Artist> opt = artistRepository.findById(id);

        if (!opt.isPresent()) {
            throw new NotFoundException();
        }

        return opt.get();
    }

    @Transactional
    public Artist createArtist(ArtistRequest request) throws InterruptedException{
        
        Optional<User> userOptional = userRepository.findByMail(request.getMail());
        if (userOptional.isPresent()) {
            throw new UserInExistanceException();
        }

        User userNew = new User();
        String encodePassword = this.passwordEncoder.encode(request.getPassword());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
         
        userNew.setName(request.getName());
        userNew.setMail(request.getMail());
        userNew.setPassword(encodePassword);
        userNew.setLocation(request.getLocation());
        userNew.setPicture(request.getPicture());
        userNew.setDateRegister(dtf.format(LocalDateTime.now()).toString());

        userRepository.save(userNew);

        Artist artistNew = new Artist();

        artistNew.setName(request.getName());
        artistNew.setUser(userNew);
        artistNew.setFollowers(0);

        String subject = "Registro exitoso";
        String text = "Bienvenido, se ha registrado exitosamente " + request.getName();

        mailService.sendEmail(request.getMail(), request.getPassword(), subject, text);
        artistRepository.save(artistNew);

        return artistNew;
    }

    @Transactional
    public Artist editArtist(Integer id, ArtistRequest request) {

        Optional<Artist> artistEditOptional = artistRepository.findById(id);
        if (!artistEditOptional.isPresent()) {
            throw new NotFoundException();
        }

        Optional<User> userOptional = userRepository.findByMail(request.getMail());
        if (userOptional.isPresent()) {
            throw new UserInExistanceException();
        }

        artistEditOptional.get().setName(request.getName());
        artistEditOptional.get().getUser().setName(request.getName());
        artistEditOptional.get().getUser().setMail(request.getMail());

        artistEditOptional.get().getUser().setLocation(request.getLocation());
        artistEditOptional.get().getUser().setPassword(request.getPassword());
        artistEditOptional.get().getUser().setPicture(request.getPassword());

        artistRepository.save(artistEditOptional.get());

        return artistEditOptional.get();  
    }

    @Transactional
    public Void deleteArtist(Integer id) {

        Optional<Artist> Optional = artistRepository.findById(id);
        
        if(!Optional.isPresent()){
            throw new NotFoundException();
        }

        playlistRepository.deleteAllByUserId(Optional.get().getUser().getId());
        songPlayRepository.deleteAllByUserId(Optional.get().getUser().getId());
        podcastPlayRepository.deleteAllByUserId(Optional.get().getUser().getId());
        followArtistRepository.deleteAllByUserId(Optional.get().getUser().getId());
        songRepository.deleteAllByArtistId(id);
        userRepository.deleteById(Optional.get().getUser().getId());  
        artistRepository.delete(Optional.get()); 
        return null;
        
    }
}

