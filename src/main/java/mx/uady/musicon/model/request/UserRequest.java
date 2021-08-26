package mx.uady.musicon.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest {
    
    @NotEmpty
    @Size(min = 1, max = 255)
    private String name;

    @NotNull
    @Size(min = 5, max = 50)
    @NotEmpty
    @Pattern (regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", 
                message = "correo inválido")
    private String mail;

    @NotNull
    @NotEmpty
    @Pattern (regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
                message = "contraseña inválida")
    private String password;

    @NotEmpty
    @Size(min = 1, max = 255)
    private String location;

    @Size(min = 1, max = 255)
    private String picture;

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    


}
