package mx.uady.musicon.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "playlists")
public class Playlist {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonBackReference
    private User user;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "location")
    private String location;
  
    @Column(name = "url_cover")
    private String urlCover;

    @Column(name = "genre")
    private String genre;

    @Column(name = "rating")
    private float rating;


    public Playlist(){

    }

    public Playlist(String name, User user, Integer duration, String location, String genre, float rating) {
        this.name = name;
        this.user = user;
        this.duration = duration;
        this.location = location;
        this.genre = genre;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getUrlCover() {
        return urlCover;
    }

    public void setUrlCover(String urlCover) {
        this.urlCover = urlCover;
    }

    @Override
    public String toString() {
        return "Playlist [duration=" + duration + ", genre=" + genre + ", id=" + id + ", location=" + location
                + ", name=" + name + ", rating=" + rating + ", urlCover=" + urlCover + ", user=" + user + "]";
    }






    
    
}
