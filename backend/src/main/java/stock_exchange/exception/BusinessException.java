package stock_exchange.exception;

public class BusinessException extends RuntimeException {

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {

        super(message);
    }

}
