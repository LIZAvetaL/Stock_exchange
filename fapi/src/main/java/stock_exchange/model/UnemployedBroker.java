package stock_exchange.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnemployedBroker {
    private int id;
    private String name;
    private String status;
    private String exchange;
}
