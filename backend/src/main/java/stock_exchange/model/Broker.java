package stock_exchange.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "brokers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Broker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_broker")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer", referencedColumnName = "id")
    private User employer;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "broker_status", referencedColumnName = "id")
    private Status status;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "exchange", referencedColumnName = "id")
    private StockExchange exchange;
}
