package Code.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import Code.Model.Ticket;

public class TicketRepository {
    private final Map<UUID, Ticket> tickets;

    public TicketRepository() {
        this.tickets = new HashMap<>();
    }

    public void save(Ticket ticket) {
        tickets.put(ticket.getId(), ticket);
    }

    public Optional<Ticket> findById(UUID id) {
        return Optional.ofNullable(tickets.get(id));
    }

    public void update(Ticket ticket) {
        tickets.put(ticket.getId(), ticket);
    }

}
