package crud.review.api.infrastructure.exceptions;

import com.auth0.jwt.exceptions.JWTVerificationException;
import crud.review.api.model.exception.AppointmentValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandling {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity notFoundHandler() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity badRequestHandler(MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ErrorHandlerViewModel::new).toList());
    }

    @ExceptionHandler(AppointmentValidationException.class)
    public ResponseEntity appointmentValidationHandler(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity badCredentialHandler() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials.");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity authenticationErrorHandler() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity accessDeniedHandler() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity internalServerErrorHandler(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " +ex.getLocalizedMessage());
    }

    private record ErrorHandlerViewModel(String campo, String message) {
        public ErrorHandlerViewModel(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
