package za.co.gpbrij.origination.api;
import java.time.Instant;
import java.util.Map;
public record ApiError(Instant timestamp,int status,String error,String message,String path,
 String correlationId,Map<String,String> fieldErrors) {}