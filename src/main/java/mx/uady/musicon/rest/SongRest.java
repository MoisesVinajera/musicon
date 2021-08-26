package mx.uady.musicon.rest;

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

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;

import mx.uady.musicon.model.Song;
import mx.uady.musicon.model.request.SongRequest;
import mx.uady.musicon.service.SongService;

@RestController
@RequestMapping("/api")
public class SongRest {
    
    @Autowired
    private SongService songService;

    @GetMapping("/songs")
    public ResponseEntity<List<Song>> getSongs() {
        return ResponseEntity.ok().body(songService.getSongs());
    }

    @GetMapping("/songs/find") 
    public ResponseEntity<List<Song>> searchSong(@RequestParam("name") String name)  {
        return ResponseEntity.ok().body(songService.searchSongByName(name));
    }

    @GetMapping("/songs/findByGenre") 
    public ResponseEntity<List<Song>> searchSongByGenre(@RequestParam("genre") String genre)  {
        return ResponseEntity.ok().body(songService.searchSongByGenre(genre));
    }

    @GetMapping("/songs/findByLocation") 
    public ResponseEntity<List<Song>> searchSongByLocation(@RequestParam("location") String location)  {
        return ResponseEntity.ok().body(songService.searchSongByLocation(location));
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<Song> getSong(@PathVariable Integer id) {
        Song song = songService.getSong(id);
        return ResponseEntity.status(HttpStatus.OK).body(song);
    }

    @PostMapping("/songs") 
    public ResponseEntity<Song> createSong(@Valid @RequestBody SongRequest request) throws URISyntaxException{
        Song songNew = songService.createSong(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(songNew);
    }

    @PutMapping("/songs/{id}") 
    public ResponseEntity<Song> editSong(@Valid @PathVariable Integer id,@Valid @RequestBody SongRequest request) {
        return ResponseEntity.ok().body(songService.editSong(id, request));
    }

    @DeleteMapping("/songs/{id}") 
    public ResponseEntity<Void> deleteSong(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(songService.deleteSong(id));
    }
}
