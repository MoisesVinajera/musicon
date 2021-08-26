package mx.uady.musicon.rest.relationsRest;

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

import mx.uady.musicon.model.relations.PlaylistSongs;
import mx.uady.musicon.model.request.PlaylistSongsRequest;
import mx.uady.musicon.service.relationsService.PlaylistSongsService;

@RestController
@RequestMapping("/api")
public class PlaylistSongsRest {
    
    @Autowired
    private PlaylistSongsService playlistSongsService;

    @GetMapping("/playlistsongs")
    public ResponseEntity<List<PlaylistSongs>> getPlaylistSongs() {
        return ResponseEntity.ok().body(playlistSongsService.getPlaylistSongs());
    }

    @GetMapping("/playlistsongs/find") 
    public ResponseEntity<List<PlaylistSongs>> searchPlaylistSongs(@RequestParam("order") Integer order)  {
        return ResponseEntity.ok().body(playlistSongsService.searchPlaylistSongByOrder(order));
    }

    @GetMapping("/playlistsongs/{id}")
    public ResponseEntity<PlaylistSongs> getPlaylistSong(@PathVariable Integer id) {
        PlaylistSongs playlistSongs = playlistSongsService.getPlaylistSong(id);
        return ResponseEntity.status(HttpStatus.OK).body(playlistSongs);
    }

    @PostMapping("/playlistsongs") 
    public ResponseEntity<PlaylistSongs> createPlaylistSong(@Valid @RequestBody PlaylistSongsRequest request) throws URISyntaxException{
        PlaylistSongs playlistSongs = playlistSongsService.createPlaylistSong(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(playlistSongs);
    }

    @PutMapping("/playlistsongs/edit") 
    public ResponseEntity<PlaylistSongs> editPlaylistSong(@Valid @RequestBody PlaylistSongsRequest request) {
        return ResponseEntity.ok().body(playlistSongsService.editPlaylistSong(request));
    }

    @DeleteMapping("/playlistsongs/{id}") 
    public ResponseEntity<Void> deletePlaylistSong(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(playlistSongsService.deletePlaylistSong(id));
    }
}
