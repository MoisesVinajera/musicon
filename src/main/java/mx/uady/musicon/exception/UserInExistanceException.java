package mx.uady.musicon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class UserInExistanceException extends RuntimeException{
    public UserInExistanceException() {
        super("El usuario ya existe");
    }

    public UserInExistanceException(String mensaje) {
        super(mensaje);
    }
}