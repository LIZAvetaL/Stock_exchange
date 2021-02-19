package stock_exchange.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.mapper.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import stock_exchange.model.response.MessageResponse;

import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler
        implements ResponseErrorHandler {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {

        return (httpResponse.getStatusCode() != HttpStatus.OK);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse)
            throws IOException {
        throw new NotFoundException(mapper.readValue(httpResponse.getBody(), MessageResponse.class).getMessage());

    }
}