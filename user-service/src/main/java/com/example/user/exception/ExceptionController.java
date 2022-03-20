package com.example.user.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApplicationExceptionDto> handleBusinessException(final ApplicationException e) throws JsonProcessingException {
        final ErrorCode errorCode = e.getErrorCode();
        final ApplicationExceptionDto response = ApplicationExceptionDto.of(errorCode);
        return ResponseEntity.status(HttpStatus.valueOf(errorCode.getStatus())).body(response);
    }

}