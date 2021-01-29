package stock_exchange.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

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
    private Date dueDate;

    private String status;
    private String broker;

}
