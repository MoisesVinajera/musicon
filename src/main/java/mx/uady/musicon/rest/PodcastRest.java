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

import mx.uady.musicon.model.Podcast;
import mx.uady.musicon.model.request.PodcastRequest;
import mx.uady.musicon.service.PodcastService;

@RestController
@RequestMapping("/api")
public class PodcastRest {
    
    @Autowired
    private PodcastService podcastService;

    @GetMapping("/podcasts")
    public ResponseEntity<List<Podcast>> getPodcast() {
        return ResponseEntity.ok().body(podcastService.getPodcast());
    }

    @GetMapping("/podcasts/find") 
    public ResponseEntity<List<Podcast>> searchPodcasts(@RequestParam("name") String name)  {
        return ResponseEntity.ok().body(podcastService.searchPodcastByName(name));
    }

    @GetMapping("/podcasts/findByGenre") 
    public ResponseEntity<List<Podcast>> searchPodcastByGenre(@RequestParam("genre") String genre)  {
        return ResponseEntity.ok().body(podcastService.searchPodcastByGenre(genre));
    }

    @GetMapping("/podcasts/findByLocation") 
    public ResponseEntity<List<Podcast>> searchPodcastByLocation(@RequestParam("location") String location)  {
        return ResponseEntity.ok().body(podcastService.searchPodcastByLocation(location));
    }

    @GetMapping("/podcasts/{id}")
    public ResponseEntity<Podcast> getPodcast(@PathVariable Integer id) {
        Podcast podcast = podcastService.getPodcast(id);
        return ResponseEntity.status(HttpStatus.OK).body(podcast);
    }

    @PostMapping("/podcasts") 
    public ResponseEntity<Podcast> createPodcast(@Valid @RequestBody PodcastRequest request) throws URISyntaxException{
        Podcast podcastNew = podcastService.createPodcast(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(podcastNew);
    }

    @PutMapping("//{id}") 
    public ResponseEntity<Podcast> editPodcast(@Valid @PathVariable Integer id,@Valid @RequestBody PodcastRequest request) {
        return ResponseEntity.ok().body(podcastService.editPodcast(id, request));
    }

    @DeleteMapping("/podcasts/{id}") 
    public ResponseEntity<Void> deletePodcast(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(podcastService.deletePodcast(id));
    }
}
