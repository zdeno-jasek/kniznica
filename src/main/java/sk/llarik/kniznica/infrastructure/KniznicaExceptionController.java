package sk.llarik.kniznica.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class KniznicaExceptionController extends ResponseEntityExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(KniznicaExceptionController.class);

    @ExceptionHandler(value= {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<Object> handleConflict( RuntimeException ex, WebRequest request) {
        logger.error("handleConflict", ex);
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

}
