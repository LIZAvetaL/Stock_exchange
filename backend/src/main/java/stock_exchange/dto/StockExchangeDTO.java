package stock_exchange.dto;


public class StockExchangeDTO {
    private int id;
    private String exchangeName;
    private String country;
    private String city;
    private String creationDate;
    private String description;

    public StockExchangeDTO(int id, String exchangeName, String country, String city,
                            String creationDate, String description) {
        this.id = id;
        this.exchangeName = exchangeName;
        this.country = country;
        this.city = city;
        this.creationDate = creationDate;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
