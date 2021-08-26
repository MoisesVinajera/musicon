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
@Table(name = "songs")
public class Song {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_artist")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "id_album")
    @JsonBackReference
    private Album album;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "location")
    private String location;

    @Column(name = "reproductions")
    private Integer reproductions;

    @Column(name = "genre")
    private String genre;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "file_name")
    private String fileName;

    public Song(){

    }

    public Song(String name, Artist artist, Album album, Integer duration, String location, Integer reproductions,
            String genre, Double rating, String fileName) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.location = location;
        this.reproductions = reproductions;
        this.genre = genre;
        this.rating = rating;
        this.fileName = fileName;
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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
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

    public Integer getReproductions() {
        return reproductions;
    }

    public void setReproductions(Integer reproductions) {
        this.reproductions = reproductions;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "Song [album=" + album + ", artist=" + artist + ", duration=" + duration + ", fileName=" + fileName
                + ", genre=" + genre + ", id=" + id + ", location=" + location + ", name=" + name + ", rating=" + rating
                + ", reproductions=" + reproductions + "]";
    }



    

    
}
