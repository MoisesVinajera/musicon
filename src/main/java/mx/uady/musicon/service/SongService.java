package mx.uady.musicon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uady.musicon.exception.AlbumNotFoundException;
import mx.uady.musicon.exception.ArtistNotFoundException;
import mx.uady.musicon.exception.NotFoundException;
import mx.uady.musicon.model.Album;
import mx.uady.musicon.model.Artist;
import mx.uady.musicon.model.Song;
import mx.uady.musicon.model.request.SongRequest;
import mx.uady.musicon.repository.AlbumRepository;
import mx.uady.musicon.repository.ArtistRepository;
import mx.uady.musicon.repository.SongRepository;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

@Service
public class SongService {
    
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired 
    private AlbumRepository albumRepository;

    @Transactional(readOnly = true)
    public List<Song> getSongs(){
        List<Song> songs = new LinkedList<>();

        songRepository.findAll().iterator().forEachRemaining(songs::add); // SELECT(id, nombre)
        
        return songs;    
    }

    public List<Song> searchSongByName(String name) {
        return songRepository.findByNameContaining(name);
    }
    
    public List<Song> searchSongByLocation(String location) {
        return songRepository.findByLocation(location);
    }

    public List<Song> searchSongByGenre(String genre) {

        List<Song> sortedSongs = songRepository.findByGenre(genre);
        sortedSongs.sort(Comparator.comparingDouble(Song::getRating).reversed());
        return sortedSongs;

    }

    @Transactional(readOnly = true)
    public Song getSong(Integer id) {

        Optional<Song> opt = songRepository.findById(id);

        if (!opt.isPresent()) {
            throw new NotFoundException();
        }

        return opt.get();
    }

    @Transactional
    public Song createSong(SongRequest request){
        
        Optional<Artist> artistOptional = artistRepository.findById(request.getArtistId());
        
        if (!artistOptional.isPresent()) {
            throw new ArtistNotFoundException();
        }
        
        Song songNew = new Song();

        if(request.getAlbumId() != null){
            Optional<Album> albumOptional = albumRepository.findById(request.getAlbumId());
            if(!albumOptional.isPresent()){
                throw new AlbumNotFoundException();
            }
            songNew.setAlbum(albumOptional.get());
        }else{
            songNew.setAlbum(null);
        }

        songNew.setName(request.getName());
        songNew.setArtist(artistOptional.get());
        songNew.setDuration(request.getDuration());
        songNew.setReproductions(0);
        songNew.setLocation(request.getLocation());
        songNew.setGenre(request.getGenre());
        songNew.setRating(request.getRating());
        songNew.setFileName(request.getFileName());

        songRepository.save(songNew);

        return songNew;
    }

    @Transactional
    public Song editSong(Integer id, SongRequest request) {

        Optional<Song> songEditOptional = songRepository.findById(id);
        //Optional<User> userExistingOptional = userRepository.findByMail(request.getMail());

        if (!songEditOptional.isPresent()) {
            throw new NotFoundException();
        }
        /*
        if (userExistingOptional.isPresent()) {
            throw new MailExistingException();
        }
        */

        Optional<Artist> artistOptional = artistRepository.findById(request.getArtistId());
        
        if (!artistOptional.isPresent()) {
            throw new ArtistNotFoundException();
        }
        
        songEditOptional.get().setName(request.getName());
        songEditOptional.get().setArtist(artistOptional.get());
        songEditOptional.get().setDuration(request.getDuration());
        songEditOptional.get().setLocation(request.getLocation());
        songEditOptional.get().setGenre(request.getGenre());
        songEditOptional.get().setRating(request.getRating());
        songEditOptional.get().setFileName(request.getFileName());

        if(request.getAlbumId() != null){
            Optional<Album> albumOptional = albumRepository.findById(request.getAlbumId());
            songEditOptional.get().setAlbum(albumOptional.get());
            albumOptional.get().getSongs().add(songEditOptional.get());
            albumRepository.save(albumOptional.get());
        }else{
            songEditOptional.get().setAlbum(null);
        }

        songRepository.save(songEditOptional.get());

        return songEditOptional.get();  
    }

    @Transactional
    public Void deleteSong(Integer id) {

        Optional<Song> songOptional = songRepository.findById(id);
        
        if(!songOptional.isPresent()){
            throw new NotFoundException();
        }
        songRepository.delete(songOptional.get()); 
        return null;
        
    }
}
