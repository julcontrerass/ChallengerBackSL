package Challenger.backend.carrera.exception;

public class DegreeNotFoundException extends RuntimeException {
    public DegreeNotFoundException(String message) {
        super(message);
        // Excepción personalizada para cuando no se encuentra una "Degree" en la base de datos.
    }
}
