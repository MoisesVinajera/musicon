package mx.uady.musicon.model.request;

import javax.validation.constraints.NotNull;

public class FollowArtistRequest {
    
    @NotNull (message = "Availability category can not be blank")
    private Integer userId;

    @NotNull (message = "Availability category can not be blank")
    private Integer artistId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    


}
