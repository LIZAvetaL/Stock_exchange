package stock_exchange.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CreateStockExchange {
    private String exchangeName;
    private String country;
    private String city;
    private String description;
}
