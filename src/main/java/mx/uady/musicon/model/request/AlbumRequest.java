package mx.uady.musicon.model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AlbumRequest {
    
    @NotEmpty
    @Size(min = 1, max = 255)
    private String name;

    @NotNull (message = "Availability category can not be blank")
    private Integer artistId;

    @NotNull
    private Integer duration;

    @NotEmpty
    @Size(min = 1, max = 255)
    private String location;

    @NotEmpty
    @Size(min = 1, max = 255)
    private String cover;

    @NotEmpty
    @Size(min = 1, max = 255)
    private String genre;

    @NotNull
    @Min(value = 0, message = "El valor no puede ser menor a 0")
    @Max(value = 5, message = "El valor no puede ser mayor a 5")
    private float rating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
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



}
