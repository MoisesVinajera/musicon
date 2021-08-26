package mx.uady.musicon.service.relationsService;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.uady.musicon.exception.NotFoundException;
import mx.uady.musicon.model.Podcast;
import mx.uady.musicon.model.User;
import mx.uady.musicon.model.relations.PodcastPlay;
import mx.uady.musicon.model.request.PodcastPlayRequest;
import mx.uady.musicon.repository.PodcastRepository;
import mx.uady.musicon.repository.UserRepository;
import mx.uady.musicon.repository.relationsRepository.PodcastPlayRepository;

@Service
public class PodcastPlayService {
    
    @Autowired
    private PodcastPlayRepository podcastPlayRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PodcastRepository podcastRepository;

    
    @Transactional(readOnly = true)
    public List<PodcastPlay> getPodcastPlays(){
        List<PodcastPlay> podcastPlays = new LinkedList<>();

        podcastPlayRepository.findAll().iterator().forEachRemaining(podcastPlays::add); 
        
        return podcastPlays;    
    }

    public List<PodcastPlay> searchPodcastPlayByLocation(String location) {
        return podcastPlayRepository.findByLocationContaining(location);
    }

    @Transactional(readOnly = true)
    public PodcastPlay getPodcastPlay(Integer id) {

        Optional<PodcastPlay> opt = podcastPlayRepository.findById(id);

        if (!opt.isPresent()) {
            throw new NotFoundException();
        }

        return opt.get();
    }

    @Transactional
    public PodcastPlay createPodcastPlay(PodcastPlayRequest request){
        
        Optional<User> userOptional = userRepository.findById(request.getUserId());
        Optional<Podcast> podcastOptional = podcastRepository.findById(request.getPodcastId());
        if (!userOptional.isPresent() || !podcastOptional.isPresent()) {
            throw new NotFoundException();
        }

        //Actualizar reproducciones
        podcastOptional.get().setReproductions(podcastOptional.get().getReproductions()+1);
        podcastRepository.save(podcastOptional.get());

        PodcastPlay podcastPlayNew = new PodcastPlay();
        
        podcastPlayNew.setUser(userOptional.get());
        podcastPlayNew.setPodcast(podcastOptional.get());
        podcastPlayNew.setLocation(userOptional.get().getLocation());

        podcastPlayRepository.save(podcastPlayNew);

        return podcastPlayNew;
    }

    @Transactional
    public List<PodcastPlay> editPodcastPlay(PodcastPlayRequest request) {

        List<PodcastPlay> podcastPlayOptional = podcastPlayRepository
                                                    .findByUserIdAndPodcastId(request.getUserId(),request.getPodcastId());
        if (podcastPlayOptional == null) {
            throw new NotFoundException();
        }

        for (PodcastPlay podcastPlay : podcastPlayOptional) {
            podcastPlay.setLocation(request.getLocation());
            podcastPlayRepository.save(podcastPlay);
        }

        return podcastPlayOptional;
    }

    @Transactional
    public Void deletePodcastPlay(Integer id) {

        Optional<PodcastPlay> Optional = podcastPlayRepository.findById(id);
        
        if(!Optional.isPresent()){
            throw new NotFoundException();
        }
        podcastPlayRepository.delete(Optional.get()); 
        return null;
        
    }
}
