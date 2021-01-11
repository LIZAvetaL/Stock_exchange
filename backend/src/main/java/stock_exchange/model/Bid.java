package stock_exchange.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "bids")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "issuer")
    private String issuer;

    @Column(name = "bid_number")
    private int bidNumber;
    @Column(name = "amount")
    private int amount;
    @Column(name = "max_price")

    private double maxPrice;
    @Column(name = "min_price")
    private double minPrice;

    @Column(name = "priority")
    private int priority;

    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "due_date")
    private Date dueDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bid_status", referencedColumnName = "id")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "broker_id", referencedColumnName = "id_broker")
    private Broker broker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private User client;
}
