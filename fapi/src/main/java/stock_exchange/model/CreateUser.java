package stock_exchange.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateUser {
    private String email;
    private String name;
    private String password;
    private String role;
}
