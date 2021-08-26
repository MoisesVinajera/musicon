package mx.uady.musicon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    
    public UserNotFoundException() {
        super("El usuario no pudo ser encontrado.");
    }

    public UserNotFoundException(String mensaje) {
        super(mensaje);
    }
}
