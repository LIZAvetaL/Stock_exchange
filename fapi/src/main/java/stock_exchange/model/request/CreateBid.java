package stock_exchange.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBid{

    private String issuer;
    private int amount;
    private double maxPrice;
    private double minPrice;
    private String priority;
    private LocalDate dueDate;
    private String type;

    private String status;
    private String broker;

}
