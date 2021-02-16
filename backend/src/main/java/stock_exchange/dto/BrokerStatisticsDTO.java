package stock_exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stock_exchange.model.Broker;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrokerStatisticsDTO {
    private BrokerDTO broker;
    private int totalAmount;
}
