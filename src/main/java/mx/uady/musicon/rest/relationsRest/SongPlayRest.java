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

import mx.uady.musicon.model.relations.SongPlay;
import mx.uady.musicon.model.request.SongPlayRequest;
import mx.uady.musicon.service.relationsService.SongPlayService;

@RestController
@RequestMapping("/api")
public class SongPlayRest {
    
    @Autowired
    private SongPlayService songPlayService;

    @GetMapping("/songplays")
    public ResponseEntity<List<SongPlay>> getSongPlays() {
        return ResponseEntity.ok().body(songPlayService.getSongPlays());
    }

    @GetMapping("/songplays/find") 
    public ResponseEntity<List<SongPlay>> searchSongPlays(@RequestParam("location") String location)  {
        return ResponseEntity.ok().body(songPlayService.searchSongPlayByLocation(location));
    }

    @GetMapping("/songplays/{id}")
    public ResponseEntity<SongPlay> getSongPlay(@PathVariable Integer id) {
        SongPlay songPlay = songPlayService.getSongPlay(id);
        return ResponseEntity.status(HttpStatus.OK).body(songPlay);
    }

    @PostMapping("/songplays/{userId}/user/{songId}/song") 
    public ResponseEntity<SongPlay> createSongPlay(@PathVariable Integer userId,@PathVariable Integer songId) throws URISyntaxException{
        SongPlay songPlay = songPlayService.createSongPlay(userId,songId);
        return ResponseEntity.status(HttpStatus.CREATED).body(songPlay);
    }

    @PutMapping("/songplays/edit/{userId}/user/{songId}/song") 
    public ResponseEntity<List<SongPlay>> editSongPlay(@PathVariable Integer userId,@PathVariable Integer songId,@Valid @RequestBody SongPlayRequest request) {
        return ResponseEntity.ok().body(songPlayService.editSongPlay(userId,songId,request));
    }

    @DeleteMapping("/songplays/{id}") 
    public ResponseEntity<Void> deleteSongPlay(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(songPlayService.deleteSongPlay(id));
    }
}
