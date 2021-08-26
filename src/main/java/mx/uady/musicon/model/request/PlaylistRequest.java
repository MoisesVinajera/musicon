package mx.uady.musicon.model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PlaylistRequest {
    
    @NotEmpty
    @Size(min = 1, max = 255)
    private String name;

    @NotNull (message = "Availability category can not be blank")
    private Integer userId;

    @NotNull
    private Integer duration;

    @NotEmpty
    @Size(min = 1, max = 255)
    private String location;

    @Size(min = 1, max = 255)
    private String urlCover;

    @NotEmpty
    @Size(min = 1, max = 255)
    private String genre;

    @Min(value = 0, message = "El valor no puede ser menor a 0")
    @Max(value = 5, message = "El valor no puede ser mayor a 5")
    private float rating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getUrlCover() {
        return urlCover;
    }

    public void setUrlCover(String urlCover) {
        this.urlCover = urlCover;
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


}
