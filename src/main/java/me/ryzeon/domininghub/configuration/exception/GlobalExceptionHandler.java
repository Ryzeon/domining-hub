package me.ryzeon.domininghub.configuration.exception;

import me.ryzeon.domininghub.entity.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 13/06/24 @ 00:32
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<MessageResponse> handleRuntimeException(RuntimeException e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }

    @ExceptionHandler({IOException.class})
    public ResponseEntity<MessageResponse> handleIOException(IOException e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body(new MessageResponse(e.getLocalizedMessage()));
    }
}
