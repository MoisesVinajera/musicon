package mx.uady.musicon.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class EntityExistsException extends RuntimeException{
    public EntityExistsException() {
        super("La entidad ya existe");
    }

    public EntityExistsException(String mensaje) {
        super(mensaje);
    }
}
