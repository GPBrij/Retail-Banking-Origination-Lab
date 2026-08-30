package za.co.gpbrij.origination.api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.NoSuchElementException;
@RestControllerAdvice
public class ApiExceptionHandler {
  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  Map<String,String> notFound(NoSuchElementException ex) { return Map.of("error", ex.getMessage()); }
}
