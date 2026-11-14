package code.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import code.model.Ticket;

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

    public List<Ticket> getAllTickets() {
        return new ArrayList<>(tickets.values());
    }
}
