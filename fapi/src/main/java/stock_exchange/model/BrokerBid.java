package stock_exchange.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrokerBid {

    private int id;
    private String issuer;

    private Long bidNumber;
    private int amount;

    private double price;
    private String broker;
}
