package stock_exchange.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateBroker {
    private String email;
    private String name;
    private String password;
    private String role;
    private String exchange;
}
