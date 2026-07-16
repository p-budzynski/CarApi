package pl.kurs.exception;

public class EngineCannotBeRemovedException extends RuntimeException {
    public EngineCannotBeRemovedException(String message) {
        super(message);
    }
}
