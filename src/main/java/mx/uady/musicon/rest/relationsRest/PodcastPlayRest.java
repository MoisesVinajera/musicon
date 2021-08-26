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

import mx.uady.musicon.model.relations.PodcastPlay;
import mx.uady.musicon.model.request.PodcastPlayRequest;
import mx.uady.musicon.service.relationsService.PodcastPlayService;

@RestController
@RequestMapping("/api")
public class PodcastPlayRest {
    
    @Autowired
    private PodcastPlayService podcastPlayService;

    @GetMapping("/podcastplays")
    public ResponseEntity<List<PodcastPlay>> getPodcastPlays() {
        return ResponseEntity.ok().body(podcastPlayService.getPodcastPlays());
    }

    @GetMapping("/podcastplays/find") 
    public ResponseEntity<List<PodcastPlay>> searchPodcastPlays(@RequestParam("location") String location)  {
        return ResponseEntity.ok().body(podcastPlayService.searchPodcastPlayByLocation(location));
    }

    @GetMapping("/podcastplays/{id}")
    public ResponseEntity<PodcastPlay> getPodcastPlay(@PathVariable Integer id) {
        PodcastPlay podcastPlay = podcastPlayService.getPodcastPlay(id);
        return ResponseEntity.status(HttpStatus.OK).body(podcastPlay);
    }

    @PostMapping("/podcastplays") 
    public ResponseEntity<PodcastPlay> createPodcastPlay(@Valid @RequestBody PodcastPlayRequest request) throws URISyntaxException{
        PodcastPlay podcastPlay = podcastPlayService.createPodcastPlay(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(podcastPlay);
    }

    @PutMapping("/podcastplays/edit") 
    public ResponseEntity<List<PodcastPlay>> editPodcastPlay(@Valid @RequestBody PodcastPlayRequest request) {
        return ResponseEntity.ok().body(podcastPlayService.editPodcastPlay(request));
    }

    @DeleteMapping("/podcastplays/{id}") 
    public ResponseEntity<Void> deletePodcastPlay(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(podcastPlayService.deletePodcastPlay(id));
    }
}
