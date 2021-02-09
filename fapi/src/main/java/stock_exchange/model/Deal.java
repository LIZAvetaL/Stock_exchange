package stock_exchange.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Deal {

    private int id;
    private int dealNumber;
    private int amount;
    private Double price;
    private Double totalPrice;
    private Date bargainDate;

    private String seller;
    private String buyer;
}
