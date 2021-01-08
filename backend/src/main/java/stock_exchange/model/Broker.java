package stock_exchange.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "brokers")
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

    public int getId() {
        return id;
    }

    public void setId(int idBroker) {
        this.id = idBroker;
    }

    public User getEmployer() {
        return employer;
    }

    public void setEmployer(User employer) {
        this.employer = employer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public StockExchange getExchange() {
        return exchange;
    }

    public void setExchange(StockExchange exchange) {
        this.exchange = exchange;
    }
}
