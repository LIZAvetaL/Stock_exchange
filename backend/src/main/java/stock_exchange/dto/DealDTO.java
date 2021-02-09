package stock_exchange.dto;

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
public class DealDTO {
    private int id;
    private int dealNumber;
    private int amount;
    private Double price;
    private Double totalPrice;
    private LocalDate bargainDate;
    private String seller;
    private String buyer;

}
