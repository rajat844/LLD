package Code.Service;

import java.util.Optional;
import java.util.UUID;

import Code.Model.Ticket;
import Code.Model.TicketStatus;
import Code.Model.TicketType;
import Code.Repository.TicketRepository;

public class TicketService {
    TicketRepository ticketRepository;
    AgentService agentService;

    public TicketService(TicketRepository ticketRepository, AgentService agentService) {
        this.ticketRepository = ticketRepository;
        this.agentService = agentService;
    }

    public Ticket createTicket(String title, String description, TicketType ticketType) {
        Ticket ticket = new Ticket(title, description, ticketType);
        ticketRepository.save(ticket);
        boolean assigned = agentService.assignTicket(ticket);
        if (!assigned) {
            return ticket;
        }

        ticketRepository.update(ticket);
        return ticket;
    }

    public Optional<Ticket> getTicket(UUID ticketId) {
        return ticketRepository.findById(ticketId);
    }

    public boolean updateTicket(UUID ticketId, String description) {
        Optional<Ticket> result = ticketRepository.findById(ticketId);
        if (result.isEmpty())
            return false;

        Ticket ticket = result.get();
        if (ticket.getStatus() == TicketStatus.RESOLVED)
            return false;

        ticket.updateDescription(description);
        ticketRepository.update(ticket);
        return true;
    }

    public boolean resolveTicket(UUID ticketId) {
        Optional<Ticket> result = ticketRepository.findById(ticketId);
        if (result.isEmpty())
            return false;

        Ticket ticket = result.get();

        try {
            ticket.resolve();
        } catch (IllegalStateException e) {
            return false;
        }

        ticketRepository.update(ticket);
        return true;
    }

    public boolean changeAgent(UUID ticketId, UUID agentId) {
        Optional<Ticket> result = ticketRepository.findById(ticketId);
        if (result.isEmpty())
            return false;

        Ticket ticket = result.get();
        if (ticket.getStatus() == TicketStatus.RESOLVED)
            return false;

        boolean changed = agentService.changeAgent(ticket, agentId);
        if (changed) {
            ticketRepository.update(ticket);
        }

        return changed;
    }
}
