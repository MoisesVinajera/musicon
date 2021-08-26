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
import mx.uady.musicon.model.Song;

@Entity
@Table(name = "playlists_songs")
public class PlaylistSongs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_song")
    private Song song;


    @ManyToOne
    @JoinColumn(name = "id_playlist")
    private Playlist playlist;

    @Column(name = "order_song")
    private Integer orderSong;

    public PlaylistSongs(){

    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }



    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public Integer getOrderSong() {
        return orderSong;
    }

    public void setOrderSong(Integer orderSong) {
        this.orderSong = orderSong;
    }

    @Override
    public String toString() {
        return "PlaylistSongs [id=" + id + ", orderSong=" + orderSong + ", playlist=" + playlist + ", song=" + song
                + "]";
    }





    
}
