package code.model;

public class ProductTicket extends CompositeTicket {

    public ProductTicket(String name, String description) {
        super(name, description, TicketType.PRODUCT);
    }

    public ProductTicket(String name) {
        this(name, "");
    }
}
