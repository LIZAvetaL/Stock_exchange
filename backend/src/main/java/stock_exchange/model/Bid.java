package stock_exchange.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "bids")
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
    private Long bidNumber;
    @Column(name = "amount")
    private int amount;
    @Column(name = "max_price")

    private double maxPrice;
    @Column(name = "min_price")
    private double minPrice;

    @Column(name = "priority")
    private String priority;
    @Column(name = "type")
    private String type;

    @Column(name = "creation_date")
    private LocalDate creationDate;
    @Column(name = "due_date")
    private LocalDate dueDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bid_status", referencedColumnName = "id")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "broker_id", referencedColumnName = "id")
    private Broker broker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private User client;
}
