package stock_exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateBrokerDTO {
    private String email;
    private String name;
    private String password;
    private String role;
    private String exchange;
}
