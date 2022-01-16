package pl.tul.emailsemotions.exceptions;

import org.hibernate.exception.GenericJDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class ExceptionHandlerController {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerController.class);
    private static final String OPTIMISTIC_LOCK_CODE = "optimistic_lock";
    private static final String INVALID_INPUT_CHARS_CODE = "invalid_input_characters";
    private static final String INVALID_INPUT_CHARS_MSG =
            "failed to save input, input contains not supported characters";
    private static final String INVALID_TEXT_INPUT = "Incorrect string value:";

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<GeneralError> handle(ObjectNotFoundException e) {
        logInfo(e);
        return ExceptionTransformer.transform(e).to(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WrongArgumentException.class)
    public ResponseEntity<GeneralError> handle(WrongArgumentException e) {
        logInfo(e);
        return ExceptionTransformer.transform(e).to(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalActionException.class)
    public ResponseEntity<GeneralError> handle(IllegalActionException e) {
        logInfo(e);
        return ExceptionTransformer.transform(e).to(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedActionException.class)
    public ResponseEntity<GeneralError> handle(UnauthorizedActionException e) {
        logInfo(e);
        return ExceptionTransformer.transform(e).to(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(WrongStateException.class)
    public ResponseEntity<GeneralError> handle(WrongStateException e) {
        logInfo(e);
        return ExceptionTransformer.transform(e).to(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<GeneralError> handle(ObjectOptimisticLockingFailureException e) {
        logError(e);
        return ExceptionTransformer.transform(OPTIMISTIC_LOCK_CODE).to(HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<GeneralError> handle(ValidationException e) {
        logError(e);
        return ExceptionTransformer.transform(e).to(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyTakenException.class)
    public ResponseEntity<GeneralError> handle(AlreadyTakenException e) {
        logError(e);
        return ExceptionTransformer.transform(e).to(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JpaSystemException.class)
    public ResponseEntity<GeneralError> handle(JpaSystemException e) {
        final boolean sqlExceptionInstance =
                e.getCause().getCause() != null && e.getCause().getCause() instanceof SQLException;

        final boolean containsInvalidTextInputMsg = e.getCause().getCause().getMessage().startsWith(INVALID_TEXT_INPUT);

        final boolean invalidInputException =
                e.getCause() instanceof GenericJDBCException && sqlExceptionInstance && containsInvalidTextInputMsg;

        logError(e);
        if (invalidInputException) {
            return new ExceptionTransformer(INVALID_INPUT_CHARS_CODE, INVALID_INPUT_CHARS_MSG)
                    .to(HttpStatus.BAD_REQUEST);
        } else {
            throw e;
        }
    }

    private void logInfo(ApplicationException e) {
        LOG.info("handle exception: code: {}, msg: {}", e.getCode(), e.getMessage(), e);
    }

    private void logError(Exception e) {
        LOG.error("handle exception", e);
    }

    private static class ExceptionTransformer {

        private final String code;
        private final String message;

        private ExceptionTransformer(String code, String message) {
            this.code = code;
            this.message = message;
        }

        static ExceptionTransformer transform(ApplicationException e) {
            return new ExceptionTransformer(e.getCode(), e.getMessage());
        }

        static ExceptionTransformer transform(String code) {
            return new ExceptionTransformer(code, null);
        }

        ResponseEntity<GeneralError> to(HttpStatus httpStatus) {
            return ResponseEntity.status(httpStatus).body(
                    GeneralError.builder().code(code).message(message).build());
        }

    }

}
