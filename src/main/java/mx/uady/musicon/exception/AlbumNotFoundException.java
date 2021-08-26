package mx.uady.musicon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AlbumNotFoundException extends RuntimeException{
    public AlbumNotFoundException() {
        super("El album no pudo ser encontrado.");
    }

    public AlbumNotFoundException(String mensaje) {
        super(mensaje);
    }
}
