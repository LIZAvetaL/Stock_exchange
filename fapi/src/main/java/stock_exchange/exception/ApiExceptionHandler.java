package stock_exchange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import stock_exchange.model.response.MessageResponse;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<String> handleModelNotFoundException(NotFoundException e) {

        return new ResponseEntity( new MessageResponse(e.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<MessageResponse> handleModelException(Exception ex) {

        MessageResponse messageResponse=new MessageResponse(ex.getMessage());
        return new ResponseEntity( messageResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }


}
