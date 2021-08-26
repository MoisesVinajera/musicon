package mx.uady.musicon.model.relations;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.uady.musicon.model.Artist;
import mx.uady.musicon.model.User;

@Entity
@Table(name = "followers_artist")
public class FollowArtist {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_artist")
    private Artist artist;

    public FollowArtist(){

    }

    public FollowArtist(User user, Artist artist) {
        this.user = user;
        this.artist = artist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "FollowArtist [artist=" + artist + ", id=" + id + ", user=" + user + "]";
    }

    

    
}
