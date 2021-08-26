package mx.uady.musicon.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "mail")
    private String mail;

    @Column(name = "password")
    @JsonIgnore 
    private String password;

    @Column(name = "location")
    private String location;

    @Column(name = "picture")
    private String picture; 

    @Column(name = "date_Register")
    private String dateRegister;

    @Transient
    @JsonIgnore
    private String token;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Playlist> playlist;

    public User(){
        
    }

    public User(String mail, String password, String location, String picture, String dateRegister,
            List<Playlist> playlist) {
        this.mail = mail;
        this.password = password;
        this.location = location;
        this.picture = picture;
        this.dateRegister = dateRegister;
        this.playlist = playlist;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(String dateRegister) {
        this.dateRegister = dateRegister;
    }

    public List<Playlist> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<Playlist> playlist) {
        this.playlist = playlist;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User [dateRegister=" + dateRegister + ", id=" + id + ", location=" + location + ", mail=" + mail
                + ", name=" + name + ", password=" + password + ", picture=" + picture + ", playlist=" + playlist
                + ", token=" + token + "]";
    }


}