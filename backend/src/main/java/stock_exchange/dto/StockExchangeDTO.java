package stock_exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class StockExchangeDTO {
    private int id;
    private String exchangeName;
    private String country;
    private String city;
    private LocalDate creationDate;
    private String description;
    private String status;
    private String owner;
}
