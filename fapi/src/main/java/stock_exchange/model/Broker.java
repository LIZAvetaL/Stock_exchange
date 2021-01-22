package stock_exchange.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Broker  {

    private int id;
    private String name;
    private String employer;
    private String status;
    private String exchange;
}
