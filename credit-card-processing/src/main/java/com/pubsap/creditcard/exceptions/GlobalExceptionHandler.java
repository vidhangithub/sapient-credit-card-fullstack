package com.pubsap.creditcard.exceptions;

import com.pubsap.creditcard.models.ApiError;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {
    private final int FOUR = 4;
    private final int ONE = 1;
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {
                final  StringBuilder builder = new StringBuilder();
                builder.append(ex.getMessage());
                if (ex.getSupportedHttpMethods().size() > ONE) {
                    builder.append(", Supported Methods are ");
                    ex.getSupportedHttpMethods().forEach(method -> builder.append(method + " "));
                } else {
                    builder.append(", Supported Method is ");
                    ex.getSupportedHttpMethods().forEach(method -> builder.append(method));
                }
                return  buildResponseEntity(status, builder.toString(), request);
    }

    @ExceptionHandler(ApiRunTimeException.class)
    protected  ResponseEntity<Object> handleApiRunTimeException(ApiRunTimeException ex, WebRequest request) {
        return buildResponseEntity(HttpStatus.valueOf(ex.getStatus()), ex.getErrorMessage(), request);
    }

    private ResponseEntity<Object> buildResponseEntity(HttpStatus status, String message , WebRequest request) {
        String path = request.getDescription(false);
        String requestUri = "";
        if (path.startsWith("uri=")) {
            requestUri = path.substring(FOUR);
        }
        ApiError apiErrorResponse = new ApiError(
                new Timestamp(System.currentTimeMillis()),
                status.value(),
                status,
                message,
                requestUri
        );
        return  ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(apiErrorResponse);
    }
}
