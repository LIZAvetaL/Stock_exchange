package stock_exchange.config;

public enum StatusConst {
    OPEN("Open"),
    CLOSED("Closed"),

    EMPLOYED("Employed"),
    UNEMPLOYED("Unemployed"),

    DRAFT("Draft"),
    ACTIVE("Active"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled"),

    SALE("Sale"),
    BUY("Buy"),

    BLOCK("Block"),
    UNBLOCK("Unblock");

    private String name;

    StatusConst(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
