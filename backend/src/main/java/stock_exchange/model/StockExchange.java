package stock_exchange.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "stock_exchange")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "exchange_name")
    private String exchangeName;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exchange_status", referencedColumnName = "id")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private User owner;

}
