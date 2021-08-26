package mx.uady.musicon.model.request;

import javax.validation.constraints.NotNull;

public class PlaylistSongsRequest {
    
    @NotNull (message = "Availability category can not be blank")
    private Integer songId;

    @NotNull (message = "Availability category can not be blank")
    private Integer playlistId;

    @NotNull
    private Integer orderSong;

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public Integer getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(Integer playlistId) {
        this.playlistId = playlistId;
    }

    public Integer getOrderSong() {
        return orderSong;
    }

    public void setOrderSong(Integer orderSong) {
        this.orderSong = orderSong;
    }



    
}
