package pl.kurs.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.kurs.dto.ExceptionResponseDto;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleCarNotFoundException(CarNotFoundException exception) {
        ExceptionResponseDto response = new ExceptionResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND.toString(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(response);
    }

    @ExceptionHandler(EngineNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleEngineNotFoundException(EngineNotFoundException exception) {
        ExceptionResponseDto response = new ExceptionResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND.toString(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(response);
    }

    @ExceptionHandler(OwnerNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleOwnerNotFoundException(OwnerNotFoundException exception) {
        ExceptionResponseDto response = new ExceptionResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND.toString(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(response);
    }

    @ExceptionHandler(EngineCannotBeRemovedException.class)
    public ResponseEntity<ExceptionResponseDto> handleEngineCannotBeRemovedException(EngineCannotBeRemovedException exception) {
        ExceptionResponseDto response = new ExceptionResponseDto(exception.getMessage(), HttpStatus.CONFLICT.toString(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(response);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ExceptionResponseDto> handleDuplicateResourceException(DuplicateResourceException exception) {
        ExceptionResponseDto response = new ExceptionResponseDto(exception.getMessage(), HttpStatus.CONFLICT.toString(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponseDto> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        ExceptionResponseDto response = new ExceptionResponseDto("Request cannot be processed due to data conflict", HttpStatus.CONFLICT.toString(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String errorMessages = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(err -> {
                    if (err instanceof FieldError fieldError) {
                        return fieldError.getDefaultMessage();
                    }
                    return err.getDefaultMessage();
                })
                .collect(Collectors.joining("; "));

        ExceptionResponseDto response = new ExceptionResponseDto(errorMessages, HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponseDto> handleConstraintViolationException(ConstraintViolationException exception) {
        ExceptionResponseDto response = new ExceptionResponseDto(exception.getMessage(), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(response);
    }

}
