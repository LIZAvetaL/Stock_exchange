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
public class Bid {

    private int id;
    private String issuer;
    private int bidNumber;
    private int amount;
    private double maxPrice;
    private double minPrice;
    private int priority;
    private Date creationDate;
    private Date dueDate;

    private String status;
    private String broker;
    private String client;
}
