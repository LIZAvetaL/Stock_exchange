package stock_exchange.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "deals")
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "amount")
    private int amount;
    @Column(name = "price")
    private Double price;
    @Column(name = "total_price")
    private Double totalPrice;
    @Column(name = "bargain_date")
    private Date bargainDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "seller", referencedColumnName = "id_broker")
    private Broker seller;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer", referencedColumnName = "id_broker")
    private Broker buyer;

    public Deal( int amount, Double totalPrice, Broker seller, Broker buyer) {
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.seller = seller;
        this.buyer = buyer;
    }

    public Deal() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getBargainDate() {
        return bargainDate;
    }

    public void setBargainDate(Date bargainDate) {
        this.bargainDate = bargainDate;
    }

    public Broker getSeller() {
        return seller;
    }

    public void setSeller(Broker seller) {
        this.seller = seller;
    }

    public Broker getBuyer() {
        return buyer;
    }

    public void setBuyer(Broker buyer) {
        this.buyer = buyer;
    }
}
