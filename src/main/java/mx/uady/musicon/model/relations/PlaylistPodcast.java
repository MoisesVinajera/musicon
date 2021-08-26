package mx.uady.musicon.model.relations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.uady.musicon.model.Playlist;
import mx.uady.musicon.model.Podcast;

@Entity
@Table(name = "playlists_podcasts")
public class PlaylistPodcast {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_podcast")
    private Podcast podcast;

    @ManyToOne
    @JoinColumn(name = "id_playlist")
    private Playlist playlist;

    @Column(name = "order_podcast")
    private Integer order;

    public PlaylistPodcast(){

    }

    public PlaylistPodcast(Podcast podcast, Playlist playlist, Integer order) {
        this.podcast = podcast;
        this.playlist = playlist;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Podcast getPodcast() {
        return podcast;
    }

    public void setPodcast(Podcast podcast) {
        this.podcast = podcast;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "PlaylistPodcast [id=" + id + ", order=" + order + ", playlist=" + playlist + ", podcast=" + podcast
                + "]";
    }



    
}
