package stock_exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrokerBidDTO {

    private int id;
    private String issuer;

    private Long bidNumber;
    private int amount;

    private double price;
    private String broker;
}
