package code.model;

public class HumanResourceTicket extends CompositeTicket {

    public HumanResourceTicket(String name, String description) {
        super(name, description, TicketType.HUMAN_RESOURCE);
    }

    public HumanResourceTicket(String name) {
        this(name, "");
    }
}
