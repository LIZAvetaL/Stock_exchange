package stock_exchange.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockExchange {

    private int id;
    private String exchangeName;
    private String country;
    private String city;
    private LocalDate creationDate;
    private String description;
    private String status;

    private String owner;
}
