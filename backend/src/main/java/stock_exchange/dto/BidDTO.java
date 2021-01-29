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
public class BidDTO {
    private int id;
    private String issuer;

    private Long bidNumber;
    private int amount;

    private double maxPrice;
    private double minPrice;
    private String priority;

    private LocalDate creationDate;
    private LocalDate dueDate;

    private String status;
    private String broker;
    private String client;

}
