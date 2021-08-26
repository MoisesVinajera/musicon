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

import mx.uady.musicon.model.Artist;
import mx.uady.musicon.model.Playlist;
import mx.uady.musicon.model.Podcast;
import mx.uady.musicon.model.Song;
import mx.uady.musicon.model.User;
import mx.uady.musicon.model.request.ArtistRequest;
import mx.uady.musicon.service.ArtistService;


@RestController
@RequestMapping("/api")
public class ArtistRest {
    
    @Autowired
    private ArtistService artistService;

    @GetMapping("/artists")
    public ResponseEntity<List<Artist>> getArtists() {
        return ResponseEntity.ok().body(artistService.getArtists());
    }

    @GetMapping("/artists/find") 
    public ResponseEntity<List<Artist>> searchArtist(@RequestParam("name") String name)  {
        return ResponseEntity.ok().body(artistService.searchArtistByName(name));
    }

    @GetMapping("/artists/{id}")
    public ResponseEntity<Artist> getArtist(@PathVariable Integer id) {
        Artist artist = artistService.getArtist(id);
        return ResponseEntity.status(HttpStatus.OK).body(artist);
    }

    @GetMapping("/artists/{id}/songs")
    public ResponseEntity<List<Song>> getSongsByArtist(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(artistService.getSongsByArtist(id));
    }

    @GetMapping("/artists/{id}/podcasts")
    public ResponseEntity<List<Podcast>> getPodcastsByArtist(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(artistService.getPodcastsByArtist(id));
    }

    @GetMapping("/artists/{id}/playlists")
    public ResponseEntity<List<Playlist>> getPlaylistsByArtist(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(artistService.getPlaylistsByArtist(id));
    }

    @GetMapping("/artists/{id}/followers")
    public ResponseEntity<List<User>> getFollowersByArtist(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(artistService.getFollowersByArtist(id));
    }

    @PostMapping("/artists/register") 
    public ResponseEntity<Artist> createArtist(@Valid @RequestBody ArtistRequest request) throws URISyntaxException, InterruptedException{
        Artist artist = artistService.createArtist(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(artist);
    }

    @PutMapping("/artists/{id}") 
    public ResponseEntity<Artist> editArtist(@Valid @PathVariable Integer id,@Valid @RequestBody ArtistRequest request) {
        return ResponseEntity.ok().body(artistService.editArtist(id, request));
    }

    @DeleteMapping("/artists/{id}") 
    public ResponseEntity<Void> deleteArtist(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(artistService.deleteArtist(id));
    }
}
