package org.nthdimenzion.presentation.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 23/8/13
 * Time: 12:22 PM
 */
@ControllerAdvice
public class UnhandledExceptionResolver {

    private final static Logger logger = LoggerFactory.getLogger(UnhandledExceptionResolver.class);

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Result> handleRuntimeException(RuntimeException ex) {
        logger.error("Unhandled exception ", ex);
        return new ResponseEntity(Result.Failure("Please contact administrator"),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
