package mx.uady.musicon.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ArtistNotFoundException extends RuntimeException {

    public ArtistNotFoundException() {
        super("El artista no pudo ser encontrado.");
    }

    public ArtistNotFoundException(String mensaje) {
        super(mensaje);
    }
    
}
