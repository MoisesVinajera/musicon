package mx.uady.musicon.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SongPlayRequest {
    
    private Integer userId;

    private Integer songId;

    @NotNull
    @Size(min = 1, max = 255)
    private String location;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    
}
