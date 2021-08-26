package mx.uady.musicon.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import mx.uady.musicon.exception.AlbumNotFoundException;
import mx.uady.musicon.exception.ArtistNotFoundException;
import mx.uady.musicon.exception.NotFoundException;
import mx.uady.musicon.model.Album;
import mx.uady.musicon.model.Artist;
import mx.uady.musicon.model.Song;
import mx.uady.musicon.model.request.AlbumRequest;
import mx.uady.musicon.repository.AlbumRepository;
import mx.uady.musicon.repository.ArtistRepository;
import mx.uady.musicon.repository.SongRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {
    
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private SongRepository songRepository;

    @Transactional(readOnly = true)
    public List<Album> getAlbums(){
        List<Album> albums = new LinkedList<>();

        albumRepository.findAll().iterator().forEachRemaining(albums::add); // SELECT(id, nombre)
        
        return albums;    
    }

    public List<Album> searchAlbumByName(String name) {
        return albumRepository.findByNameContaining(name);
    }

    @Transactional(readOnly = true)
    public Album getAlbum(Integer id) {

        Optional<Album> opt = albumRepository.findById(id);

        if (!opt.isPresent()) {
            throw new NotFoundException();
        }

        return opt.get();
    }

    @Transactional(readOnly = true)
    public List<Song> getSongsByAlbum(Integer id) {

        Optional<Album> albumOpt = albumRepository.findById(id);

        if (!albumOpt.isPresent()) {
            throw new AlbumNotFoundException();
        }

        if (albumOpt.get().getSongs().isEmpty()) {
            throw new NotFoundException();
        }

        return albumOpt.get().getSongs();
    }

    @Transactional
    public Album createAlbum(AlbumRequest request){
        
        Optional<Artist> artistOptional = artistRepository.findById(request.getArtistId());
        if (!artistOptional.isPresent()) {
            throw new ArtistNotFoundException();
        }

        Album albumNew = new Album();

        albumNew.setName(request.getName());
        albumNew.setArtist(artistOptional.get());
        albumNew.setDuration(request.getDuration());
        albumNew.setLocation(request.getLocation());
        albumNew.setCover(request.getCover());
        albumNew.setGenre(request.getGenre());
        albumNew.setRating(request.getRating());

        albumRepository.save(albumNew);

        return albumNew;
    }

    @Transactional
    public Album editAlbum(Integer id, AlbumRequest request) {

        Optional<Album> albumOptional = albumRepository.findById(id);
        if (!albumOptional.isPresent()) {
            throw new NotFoundException();
        }

        Optional<Artist> artistOptional = artistRepository.findById(request.getArtistId());
        if (!artistOptional.isPresent()) {
            throw new ArtistNotFoundException();
        }

        albumOptional.get().setName(request.getName());
        albumOptional.get().setArtist(artistOptional.get());
        albumOptional.get().setDuration(request.getDuration());
        albumOptional.get().setLocation(request.getLocation());
        albumOptional.get().setCover(request.getCover());
        albumOptional.get().setGenre(request.getGenre());
        albumOptional.get().setRating(request.getRating());

        
        albumRepository.save(albumOptional.get());

        return albumOptional.get();  
    }

    @Transactional
    public Song addSong(Integer albumId, Integer songId) {

        Optional<Album> albumOptional = albumRepository.findById(albumId);
        if (!albumOptional.isPresent()) {
            throw new AlbumNotFoundException();
        }

        Optional<Song> songOptional = songRepository.findById(songId);
        if (!songOptional.isPresent()) {
            throw new NotFoundException();
        }

        songOptional.get().setAlbum(albumOptional.get());
        songRepository.save(songOptional.get());

        return songOptional.get();  
    }

    @Transactional
    public Void deleteAlbum(Integer id) {

        Optional<Album> albumOptional = albumRepository.findById(id);
        
        if(!albumOptional.isPresent()){
            throw new NotFoundException();
        }
        albumRepository.delete(albumOptional.get()); 
        return null;
        
    }
}
