package mx.uady.musicon.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mx.uady.musicon.exception.EntityExistsException;
import mx.uady.musicon.exception.NotFoundException;
import mx.uady.musicon.exception.UserInExistanceException;
import mx.uady.musicon.exception.UserNotFoundException;
import mx.uady.musicon.filter.JwtTokenUtil;
import mx.uady.musicon.model.Artist;
import mx.uady.musicon.model.Playlist;
import mx.uady.musicon.model.Podcast;
import mx.uady.musicon.model.Song;
import mx.uady.musicon.model.User;
import mx.uady.musicon.model.relations.FollowArtist;
import mx.uady.musicon.model.relations.PodcastPlay;
import mx.uady.musicon.model.relations.SongPlay;
import mx.uady.musicon.model.request.JwtRequest;
import mx.uady.musicon.model.request.UserRequest;
import mx.uady.musicon.model.response.JwtResponse;
import mx.uady.musicon.repository.ArtistRepository;
import mx.uady.musicon.repository.PlaylistRepository;
import mx.uady.musicon.repository.UserRepository;
import mx.uady.musicon.repository.relationsRepository.FollowArtistRepository;
import mx.uady.musicon.repository.relationsRepository.PodcastPlayRepository;
import mx.uady.musicon.repository.relationsRepository.SongPlayRepository;

@Service
public class UserService implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private SongPlayRepository songPlayRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired 
    private PodcastPlayRepository podcastPlayRepository;

    @Autowired
    private FollowArtistRepository followArtistRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private MailService mailService = new MailService();

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<User> opt = userRepository.findByName(username);
        if(!opt.isPresent()){
            throw new NotFoundException();
        }

        return  new org.springframework.security.core.userdetails
                    .User(opt.get().getName(), opt.get().getPassword(),new ArrayList<>());
        
	}

    @Transactional(readOnly = true)
    public List<User> getUsers(){
        List<User> users = new LinkedList<>();

        userRepository.findAll().iterator().forEachRemaining(users::add); 
        
        return users;    
    }

    public User searchUserByMail(String mail) {
        return userRepository.findByMail(mail).get();
    }

    @Transactional(readOnly = true)
    public User getUser(Integer id) {

        Optional<User> opt = userRepository.findById(id);

        if (!opt.isPresent()) {
            throw new NotFoundException();
        }

        return opt.get();
    }

    @Transactional(readOnly = true)
    public List<Playlist> getPlaylistsByUser(Integer id) {

        Optional<User> userOpt = userRepository.findById(id);

        if (!userOpt.isPresent()) {
            throw new UserNotFoundException();
        }

        List<Playlist> playlistList = playlistRepository.findByUser(userOpt.get());

        if (playlistList.isEmpty()) {
            throw new NotFoundException();
        }

        return playlistList;
    }

    @Transactional(readOnly = true)
    public List<Artist> getFollowArtitsByUser(Integer id) {

        Optional<User> userOpt = userRepository.findById(id);

        if (!userOpt.isPresent()) {
            throw new UserNotFoundException();
        }

        List<FollowArtist> followerArtistList = followArtistRepository.findByUser(userOpt.get());

        if (followerArtistList.isEmpty()) {
            throw new NotFoundException();
        }

        List<Artist> artists = new LinkedList<>();

        for (FollowArtist followArtist : followerArtistList) {
            artists.add(followArtist.getArtist());
        }
        
        return artists;
    }

    @Transactional(readOnly = true)
    public List<Song> getHistorialSongByUser(Integer id) {

        Optional<User> userOpt = userRepository.findById(id);

        if (!userOpt.isPresent()) {
            throw new UserNotFoundException();
        }

        List<SongPlay> songPlaysList = songPlayRepository.findByUser(userOpt.get());

        if (songPlaysList.isEmpty()) {
            throw new NotFoundException();
        }

        List<Song> songs = new LinkedList<>();

        for (SongPlay songPlay : songPlaysList) {
            songs.add(songPlay.getSong());
        }
        
        return songs;
    }

    @Transactional(readOnly = true)
    public List<Podcast> getHistorialPodcastsByUser(Integer id) {

        Optional<User> userOpt = userRepository.findById(id);

        if (!userOpt.isPresent()) {
            throw new UserNotFoundException();
        }

        List<PodcastPlay> podcastPlaysList = podcastPlayRepository.findByUser(userOpt.get());

        if (podcastPlaysList.isEmpty()) {
            throw new NotFoundException();
        }

        List<Podcast> podcasts = new LinkedList<>();

        for (PodcastPlay podcastPlay : podcastPlaysList) {
            podcasts.add(podcastPlay.getPodcast());
        }
        
        return podcasts;
    }

    @Transactional
    public User createUser(UserRequest request) throws InterruptedException{
        
        Optional<User> userOptional = userRepository.findByMail(request.getMail());
        if (userOptional.isPresent()) {
            throw new UserInExistanceException();
        }

        User userNew = new User();
        String encodePassword = this.passwordEncoder.encode(request.getPassword());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        String subject = "Registro exitoso";
        String text = "Bienvenido, se ha registrado exitosamente " + request.getName();

        mailService.sendEmail(request.getMail(), request.getPassword(), subject, text);

        userNew.setName(request.getName());
        userNew.setMail(request.getMail());
        userNew.setPassword(encodePassword);
        userNew.setLocation(request.getLocation());
        userNew.setPicture(request.getPicture());
        userNew.setDateRegister(dtf.format(LocalDateTime.now()).toString());

        userRepository.save(userNew);

        return userNew;
    }

    @Transactional
    public JwtResponse login(JwtRequest request, String userAgent) throws InterruptedException {
        Optional<User> userOpt = userRepository.findByMail(request.getUsername());

        if(!userOpt.isPresent() || !passwordEncoder.matches(request.getPassword(), userOpt.get().getPassword())){
            throw new NotFoundException();
        }

        final UserDetails userDetails = new org.springframework.security.core.userdetails.User(userOpt.get().getName(), userOpt.get().getPassword(),
        new ArrayList<>());

        final String token = jwtTokenUtil.generateToken(userDetails);


        userOpt.get().setToken(token);
        userRepository.save(userOpt.get()); // INSERT

        String subject = "Inicio de sesión";
        String text = "Se ha iniciado sesión en el dispositivo " + userAgent;

        mailService.sendEmail(request.getUsername(), request.getPassword(), subject, text);

        return new JwtResponse(token);

    }

    @Transactional
    public Void logOut(Integer id) {
        Optional<User> userOpt = userRepository.findById(id);

        if(!userOpt.isPresent()){
            throw new NotFoundException();
        }
        userOpt.get().setToken(null);

        return null;
    }

    @Transactional
    public User editUser(Integer id, UserRequest request) throws InterruptedException {

        Optional<User> userEditOptional = userRepository.findById(id);
        //Optional<User> userExistingOptional = userRepository.findByMail(request.getMail());

        if (!userEditOptional.isPresent()) {
            throw new NotFoundException();
        }
        /*
        if (userExistingOptional.isPresent()) {
            throw new MailExistingException();
        }
        */
        String subject = "Profile changes";
        String text = "Se han modificado algunos campos de la cuenta. Los campos modificados son los siguienteS:\n" +
                        "Nomble anterior: " + userEditOptional.get().getName() + "\n" +
                        "Ubicación anterior: " + userEditOptional.get().getLocation() + "\n" +
                        "Imagen anterior: " + userEditOptional.get().getPicture() + "\n" +
                        "Nomble actual: " + request.getName() + "\n" +
                        "Ubicación anterior: " + request.getLocation() + "\n" +
                        "Imagen anterior: " + request.getPicture();
        mailService.sendEmail(userEditOptional.get().getMail(), request.getPassword(), subject, text);

        userEditOptional.get().setName(request.getName());
        userEditOptional.get().setLocation(request.getLocation());
        userEditOptional.get().setPicture(request.getPicture());

        userRepository.save(userEditOptional.get());

        return userEditOptional.get();  
    }

    @Transactional
    public Void deleteUser(Integer id) {

        Optional<User> userOptional = userRepository.findById(id);
        
        if(!userOptional.isPresent()){
            throw new NotFoundException();
        }
        
        Optional<Artist> artistOptional = artistRepository.findByUserId(id);
        if(artistOptional.isPresent()){
            throw new EntityExistsException();
        }

        playlistRepository.deleteAllByUserId(id);
        songPlayRepository.deleteAllByUserId(id);
        podcastPlayRepository.deleteAllByUserId(id);
        followArtistRepository.deleteAllByUserId(id);
        userRepository.delete(userOptional.get()); 

        return null;
        
    }
}
