package pl.kurs.exception;

public class EngineNotFoundException extends RuntimeException {
    public EngineNotFoundException(String message) {
        super(message);
    }
}