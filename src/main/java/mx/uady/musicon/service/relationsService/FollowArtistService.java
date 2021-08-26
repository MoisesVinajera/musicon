package mx.uady.musicon.service.relationsService;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.uady.musicon.exception.EntityExistsException;
import mx.uady.musicon.exception.NotFoundException;
import mx.uady.musicon.model.Artist;
import mx.uady.musicon.model.User;
import mx.uady.musicon.model.relations.FollowArtist;
import mx.uady.musicon.repository.ArtistRepository;
import mx.uady.musicon.repository.UserRepository;
import mx.uady.musicon.repository.relationsRepository.FollowArtistRepository;

@Service
public class FollowArtistService {
    
    @Autowired
    private FollowArtistRepository followArtistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Transactional(readOnly = true)
    public List<FollowArtist> getFollowArtists(){
        List<FollowArtist> followArtists = new LinkedList<>();

        followArtistRepository.findAll().iterator().forEachRemaining(followArtists::add); 
        
        return followArtists;    
    }

    @Transactional(readOnly = true)
    public FollowArtist getFollowArtist(Integer id) {

        Optional<FollowArtist> opt = followArtistRepository.findById(id);

        if (!opt.isPresent()) {
            throw new NotFoundException();
        }

        return opt.get();
    }

    @Transactional
    public FollowArtist createFollowArtist(Integer userId, Integer artistId){
        
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Artist> artistOptional = artistRepository.findById(artistId);
        if (!userOptional.isPresent() || !artistOptional.isPresent()) {
            throw new NotFoundException();
        }

        Optional<FollowArtist> followArtistOptional = followArtistRepository.findByUserIdAndArtistId(userId,artistId);
        if (followArtistOptional.isPresent() ) {
            throw new EntityExistsException();
        }

        //Actualizando seguidores
        artistOptional.get().setFollowers(artistOptional.get().getFollowers()+1);
        artistRepository.save(artistOptional.get());

        FollowArtist followArtist = new FollowArtist();       
        followArtist.setUser(userOptional.get());
        followArtist.setArtist(artistOptional.get());

        followArtistRepository.save(followArtist);

        return followArtist;
    }

    @Transactional
    public Void deleteFollowArtist(Integer id) {

        Optional<FollowArtist> Optional = followArtistRepository.findById(id);
        
        if(!Optional.isPresent()){
            throw new NotFoundException();
        }
        //Actualizando seguidores
        Optional.get().getArtist().setFollowers(Optional.get().getArtist().getFollowers()-1);
        artistRepository.save(Optional.get().getArtist());

        followArtistRepository.delete(Optional.get()); 
        return null;
        
    }
}
