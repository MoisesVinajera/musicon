package mx.uady.musicon.model.relations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.uady.musicon.model.Podcast;
import mx.uady.musicon.model.User;

@Entity
@Table(name = "podcast_plays")
public class PodcastPlay {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_podcast")
    private Podcast podcast;

    @Column(name = "location")
    private String location;

    public PodcastPlay(){

    }


    public PodcastPlay(User user, Podcast podcast, String location) {
        this.user = user;
        this.podcast = podcast;
        this.location = location;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public Podcast getPodcast() {
        return podcast;
    }


    public void setPodcast(Podcast podcast) {
        this.podcast = podcast;
    }


    @Override
    public String toString() {
        return "PodcastPlay [id=" + id + ", location=" + location + ", podcast=" + podcast + ", user=" + user + "]";
    }

    

    
}
