package stock_exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class CreateStockExchangeDTO {
    private String exchangeName;
    private String country;
    private String city;
    private String description;
}
