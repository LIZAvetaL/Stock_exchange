package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockExchange {

    private int id;
    private String exchangeName;
    private String country;
    private String city;
    private Date creationDate;
    private String description;

    private String owner;
}
