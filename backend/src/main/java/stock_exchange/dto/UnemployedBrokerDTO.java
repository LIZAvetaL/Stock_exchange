package stock_exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnemployedBrokerDTO {
    private int id;
    private String name;
    private String status;
    private String exchange;
}
