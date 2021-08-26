package mx.uady.musicon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class MailExistingException extends RuntimeException{
    public MailExistingException() {
        super("El correo ya existe");
    }

    public MailExistingException(String mensaje) {
        super(mensaje);
    }
}