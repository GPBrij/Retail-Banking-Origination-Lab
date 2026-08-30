package za.co.gpbrij.origination.api;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
@RestControllerAdvice
public class ApiExceptionHandler {
 private ApiError error(HttpStatus status,String message,HttpServletRequest request,Map<String,String> fields){
  Object value=request.getAttribute("correlationId");
  return new ApiError(Instant.now(),status.value(),status.getReasonPhrase(),message,request.getRequestURI(),
   value==null?null:value.toString(),fields);
 }
 @ExceptionHandler(NoSuchElementException.class) @ResponseStatus(HttpStatus.NOT_FOUND)
 ApiError notFound(NoSuchElementException ex,HttpServletRequest request){return error(HttpStatus.NOT_FOUND,ex.getMessage(),request,Map.of());}
 @ExceptionHandler(IllegalStateException.class) @ResponseStatus(HttpStatus.CONFLICT)
 ApiError conflict(IllegalStateException ex,HttpServletRequest request){return error(HttpStatus.CONFLICT,ex.getMessage(),request,Map.of());}
 @ExceptionHandler(MethodArgumentNotValidException.class) @ResponseStatus(HttpStatus.BAD_REQUEST)
 ApiError validation(MethodArgumentNotValidException ex,HttpServletRequest request){
  Map<String,String> fields=ex.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(
   f->f.getField(),f->Optional.ofNullable(f.getDefaultMessage()).orElse("Invalid value"),(a,b)->a,LinkedHashMap::new));
  return error(HttpStatus.BAD_REQUEST,"Request validation failed",request,fields);
 }
}