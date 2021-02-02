package stock_exchange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import stock_exchange.response.MessageResponse;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<MessageResponse> handleModelNotFoundException(NotFoundException e) {

        MessageResponse messageResponse=new MessageResponse(e.getMessage());
        return new ResponseEntity( messageResponse, HttpStatus.NOT_ACCEPTABLE);
    }


}
