package mx.uady.musicon.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PodcastPlayRequest {
    
    @NotNull (message = "Availability category can not be blank")
    private Integer userId;

    @NotNull (message = "Availability category can not be blank")
    private Integer podcastId;

    @Size(min = 1, max = 255)
    private String location;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPodcastId() {
        return podcastId;
    }

    public void setPodcastId(Integer podcastId) {
        this.podcastId = podcastId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    
}
