package mx.edu.utez.codificacionSpringBoot.exception.customException;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}