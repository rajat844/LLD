package code.model;

public class SoftwareTicket extends CompositeTicket {

    public SoftwareTicket(String name, String description) {
        super(name, description, TicketType.SOFTWARE);
    }

    public SoftwareTicket(String name) {
        this(name, "");
    }
}
