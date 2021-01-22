package stock_exchange.dto;

public class BrokerDTO {
    private int id;
    private String name;
    private String employer;
    private String status;
    private String exchange;

    public BrokerDTO(int id, String name, String employer, String status, String exchange) {
        this.id = id;
        this.name = name;
        this.employer = employer;
        this.status = status;
        this.exchange = exchange;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
}
