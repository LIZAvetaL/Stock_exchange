package stock_exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {

    private int id;
    private String email;
    private String password;
    private String name;
    private String role;
    private String status;
}
