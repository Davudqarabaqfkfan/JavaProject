package www.com.Project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MyException.class)
    public ResponseEntity<String> handleMyException(MyException exx) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exx.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException exxx) {
        StringBuilder b = new StringBuilder("Validation error: ");
        exxx.getBindingResult().getAllErrors().forEach(err -> {
            b.append(err.getDefaultMessage()).append("; ");
        });
        return ResponseEntity.badRequest().body(b.toString());
    }

}
