package mx.uady.musicon.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "albums")
public class Album {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_artist")
    @JsonBackReference
    private Artist artist;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "location")
    private String location;

    @Column(name = "url_cover")
    private String cover;
    
    @Column(name = "genre")
    private String genre;

    @Column(name = "rating")
    private float rating;

    @OneToMany(mappedBy = "album")
    @JsonBackReference
    private List<Song> songs;

    public Album(){

    }

    public Album(String name, Artist artist, Integer duration, String location, String cover, String genre, float rating,
            List<Song> songs) {
        this.name = name;
        this.artist = artist;
        this.duration = duration;
        this.location = location;
        this.cover = cover;
        this.genre = genre;
        this.rating = rating;
        this.songs = songs;
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

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "Album [artist=" + artist + ", cover=" + cover + ", duration=" + duration + ", genre=" + genre + ", id="
                + id + ", location=" + location + ", name=" + name + ", rating=" + rating + ", songs=" + songs + "]";
    }
    
    

    
}
