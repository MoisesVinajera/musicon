package mx.uady.musicon.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uady.musicon.exception.ArtistNotFoundException;
import mx.uady.musicon.exception.NotFoundException;
import mx.uady.musicon.model.Artist;
import mx.uady.musicon.model.Podcast;
import mx.uady.musicon.model.request.PodcastRequest;
import mx.uady.musicon.repository.ArtistRepository;
import mx.uady.musicon.repository.PodcastRepository;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Service
public class PodcastService {
    
    @Autowired
    private PodcastRepository podcastRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Transactional(readOnly = true)
    public List<Podcast> getPodcast(){
        List<Podcast> podcasts = new LinkedList<>();

        podcastRepository.findAll().iterator().forEachRemaining(podcasts::add); 
        
        return podcasts;    
    }

    public List<Podcast> searchPodcastByName(String name) {
        return podcastRepository.findByNameContaining(name);
    }

    public List<Podcast> searchPodcastByLocation(String location) {
        return podcastRepository.findByLocation(location);
    }

    public List<Podcast> searchPodcastByGenre(String genre) {

        List<Podcast> sortedPodcast = podcastRepository.findByGenre(genre);
        sortedPodcast.sort(Comparator.comparingDouble(Podcast::getRating).reversed());
        return sortedPodcast;
    }

    @Transactional(readOnly = true)
    public Podcast getPodcast(Integer id) {

        Optional<Podcast> opt = podcastRepository.findById(id);

        if (!opt.isPresent()) {
            throw new NotFoundException();
        }

        return opt.get();
    }

    @Transactional
    public Podcast createPodcast(PodcastRequest request){
        
        Optional<Artist> artistOptional = artistRepository.findById(request.getArtistId());

        if(!artistOptional.isPresent()){
            throw new ArtistNotFoundException();
        }
        
        Podcast podcastNew = new Podcast();

        podcastNew.setName(request.getName());
        podcastNew.setArtist(artistOptional.get());
        podcastNew.setDuration(request.getDuration());
        podcastNew.setReproductions(0);
        podcastNew.setLocation(request.getLocation());
        podcastNew.setGenre(request.getGenre());
        podcastNew.setRating(request.getRating());
        podcastNew.setFileName(request.getFileName());

        podcastRepository.save(podcastNew);

        return podcastNew;
    }

    @Transactional
    public Podcast editPodcast(Integer id, PodcastRequest request) {

        Optional<Podcast> podcastEditOptional = podcastRepository.findById(id);

        if (!podcastEditOptional.isPresent()) {
            throw new NotFoundException();
        }

        Optional<Artist> artistOptional = artistRepository.findById(request.getArtistId());
        
        if (!artistOptional.isPresent()) {
            throw new ArtistNotFoundException();
        }
        
        podcastEditOptional.get().setName(request.getName());
        podcastEditOptional.get().setArtist(artistOptional.get());
        podcastEditOptional.get().setDuration(request.getDuration());
        podcastEditOptional.get().setLocation(request.getLocation());
        podcastEditOptional.get().setGenre(request.getGenre());
        podcastEditOptional.get().setRating(request.getRating());
        podcastEditOptional.get().setFileName(request.getFileName());

        podcastRepository.save(podcastEditOptional.get());

        return podcastEditOptional.get();  
    }

    @Transactional
    public Void deletePodcast(Integer id) {

        Optional<Podcast> podcastOptional = podcastRepository.findById(id);
        
        if(!podcastOptional.isPresent()){
            throw new NotFoundException();
        }
        podcastRepository.delete(podcastOptional.get()); 
        return null;
        
    }
}
