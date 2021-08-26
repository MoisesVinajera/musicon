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
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonBackReference
    private User user;

    @Column(name = "followers")
    private Integer followers;

    public Artist() {
    }

    public Artist(String name, User user, Integer followers) {
        this.name = name;
        this.user = user;
        this.followers = followers;
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


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public Integer getFollowers() {
        return followers;
    }


    public void setFollowers(Integer followers) {
        this.followers = followers;
    }


    @Override
    public String toString() {
        return "Artist [followers=" + followers + ", id=" + id + ", name=" + name + ", user=" + user + "]";
    }

}
