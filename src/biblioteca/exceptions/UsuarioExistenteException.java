package biblioteca.exceptions;

public class UsuarioExistenteException extends Exception {
    public UsuarioExistenteException(String message) {
        super(message);
    }
}