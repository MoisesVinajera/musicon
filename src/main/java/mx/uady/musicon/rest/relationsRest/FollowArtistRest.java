package mx.uady.musicon.rest.relationsRest;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.uady.musicon.model.relations.FollowArtist;
import mx.uady.musicon.service.relationsService.FollowArtistService;

@RestController
@RequestMapping("/api")
public class FollowArtistRest {

    @Autowired
    private FollowArtistService followArtistService;

    @GetMapping("/followArtists")
    public ResponseEntity<List<FollowArtist>> getFollowArtists() {
        return ResponseEntity.ok().body(followArtistService.getFollowArtists());
    }

    @GetMapping("/followArtists/{id}")
    public ResponseEntity<FollowArtist> getFollowArtist(@PathVariable Integer id) {
        FollowArtist followArtist = followArtistService.getFollowArtist(id);
        return ResponseEntity.status(HttpStatus.OK).body(followArtist);
    }

    @PostMapping("/followArtists/{userId}/user/{artistId}/artist") 
    public ResponseEntity<FollowArtist> createFollowArtist(@PathVariable Integer userId,@PathVariable Integer artistId) throws URISyntaxException{
        FollowArtist followArtist = followArtistService.createFollowArtist(userId,artistId);
        return ResponseEntity.status(HttpStatus.CREATED).body(followArtist);
    }

    @DeleteMapping("/followArtists/{id}") 
    public ResponseEntity<Void> deleteFollowArtist(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(followArtistService.deleteFollowArtist(id));
    }
}
