package mx.uady.musicon.rest;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.uady.musicon.model.Album;
import mx.uady.musicon.model.Song;
import mx.uady.musicon.model.request.AlbumRequest;
import mx.uady.musicon.service.AlbumService;

@RestController
@RequestMapping("/api")
public class AlbumRest {
    
    @Autowired
    private AlbumService albumService;

    @GetMapping("/albums")
    public ResponseEntity<List<Album>> getAlbums() {
        return ResponseEntity.ok().body(albumService.getAlbums());
    }

    @GetMapping("/albums/find") 
    public ResponseEntity<List<Album>> searchAlbum(@RequestParam("name") String name)  {
        return ResponseEntity.ok().body(albumService.searchAlbumByName(name));
    }

    @GetMapping("/albums/{id}")
    public ResponseEntity<Album> getAlbum(@PathVariable Integer id) {
        Album album = albumService.getAlbum(id);
        return ResponseEntity.status(HttpStatus.OK).body(album);
    }

    @GetMapping("/albums/{id}/songs")
    public ResponseEntity<List<Song>> getSongsByAlbum(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(albumService.getSongsByAlbum(id));
    }

    @PostMapping("/albums") 
    public ResponseEntity<Album> createAlbum(@Valid @RequestBody AlbumRequest request) throws URISyntaxException{
        Album album = albumService.createAlbum(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(album);
    }

    @PutMapping("/albums/{id}") 
    public ResponseEntity<Album> editAlbum(@Valid @PathVariable Integer id,@Valid @RequestBody AlbumRequest request) {
        return ResponseEntity.ok().body(albumService.editAlbum(id, request));
    }

    @PutMapping("/albums/{albumId}/song/{songId}")
    public ResponseEntity<Song> addSong(@PathVariable Integer albumId,@PathVariable Integer songId) {
        return ResponseEntity.status(HttpStatus.OK).body(albumService.addSong(albumId,songId));
    }

    @DeleteMapping("/albums/{id}") 
    public ResponseEntity<Void> deleteAlbum(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(albumService.deleteAlbum(id));
    }
}
