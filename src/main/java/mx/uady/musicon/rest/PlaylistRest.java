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

import mx.uady.musicon.model.Playlist;
import mx.uady.musicon.model.request.PlaylistRequest;
import mx.uady.musicon.service.PlaylistService;

@RestController
@RequestMapping("/api")
public class PlaylistRest {
    
    @Autowired
    private PlaylistService playlistService;

    @GetMapping("/playlists")
    public ResponseEntity<List<Playlist>> getPlaylists() {
        return ResponseEntity.ok().body(playlistService.getPlaylists());
    }

    @GetMapping("/playlists/find") 
    public ResponseEntity<List<Playlist>> searchPlaylist(@RequestParam("location") String location)  {
        return ResponseEntity.ok().body(playlistService.searchPlaylistByLocation(location));
    }

    @GetMapping("/playlists/{id}")
    public ResponseEntity<Playlist> getPlaylist(@PathVariable Integer id) {
        Playlist playlist = playlistService.getPlaylist(id);
        return ResponseEntity.status(HttpStatus.OK).body(playlist);
    }

    @PostMapping("/playlists") 
    public ResponseEntity<Playlist> createSong(@Valid @RequestBody PlaylistRequest request) throws URISyntaxException{
        Playlist songNew = playlistService.createPlaylist(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(songNew);
    }

    @PutMapping("/playlists/{id}") 
    public ResponseEntity<Playlist> editPlaylist(@Valid @PathVariable Integer id,@Valid @RequestBody PlaylistRequest request) {
        return ResponseEntity.ok().body(playlistService.editPlaylist(id, request));
    }

    @DeleteMapping("/playlists/{id}") 
    public ResponseEntity<Void> deletePlaylist(@PathVariable Integer id) throws InterruptedException {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(playlistService.deletePlaylist(id));
    }
}
