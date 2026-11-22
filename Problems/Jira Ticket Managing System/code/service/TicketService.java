package code.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import code.model.CompositeTicket;
import code.model.HumanResourceTicket;
import code.model.ProductTicket;
import code.model.QualityAnalysisTicket;
import code.model.SoftwareTicket;
import code.model.TaskStatus;
import code.model.Ticket;
import code.model.TicketType;
import code.repository.TicketRepository;

public class TicketService {
    private final TicketRepository ticketRepository;
    private final AgentService agentService;

    public TicketService(TicketRepository ticketRepository, AgentService agentService) {
        this.ticketRepository = ticketRepository;
        this.agentService = agentService;
    }

    public Ticket createTicket(String title, String description, TicketType ticketType) {
        Ticket ticket;
        if (ticketType != null) {
            switch (ticketType) {
                case SOFTWARE:
                    ticket = new SoftwareTicket(title, description);
                    break;
                case QUALITY_ANALYSIS:
                    ticket = new QualityAnalysisTicket(title, description);
                    break;
                case PRODUCT:
                    ticket = new ProductTicket(title, description);
                    break;
                case HUMAN_RESOURCE:
                case HUMAN_RELATIONS:
                    ticket = new HumanResourceTicket(title, description);
                    break;
                default:
                    ticket = new SoftwareTicket(title, description);
                    break;
            }
        } else {
            ticket = new Ticket(title, description, TicketType.SOFTWARE);
        }

        return registerAndAssignTicket(ticket);
    }

    public Ticket createTicket(Ticket ticket) {
        return registerAndAssignTicket(ticket);
    }

    private Ticket registerAndAssignTicket(Ticket ticket) {
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

    public List<Ticket> getAllTickets() {
        return ticketRepository.getAllTickets();
    }

    public boolean updateTicket(UUID ticketId, String description) {
        Optional<Ticket> result = ticketRepository.findById(ticketId);
        if (result.isEmpty()) {
            return false;
        }

        Ticket ticket = result.get();
        if (ticket.getStatus() == TaskStatus.RESOLVED) {
            return false;
        }

        ticket.updateDescription(description);
        ticketRepository.update(ticket);
        return true;
    }

    public boolean resolveTicket(UUID ticketId) {
        Optional<Ticket> result = ticketRepository.findById(ticketId);
        if (result.isEmpty()) {
            return false;
        }

        Ticket ticket = result.get();

        try {
            boolean resolved = ticket.resolve();
            if (!resolved) {
                return false;
            }
        } catch (IllegalStateException e) {
            return false;
        }

        ticketRepository.update(ticket);
        return true;
    }

    public boolean changeAgent(UUID ticketId, UUID agentId) {
        Optional<Ticket> result = ticketRepository.findById(ticketId);
        if (result.isEmpty()) {
            return false;
        }

        Ticket ticket = result.get();
        if (ticket.getStatus() == TaskStatus.RESOLVED) {
            return false;
        }

        boolean changed = agentService.changeAgent(ticket, agentId);
        if (changed) {
            ticketRepository.update(ticket);
        }

        return changed;
    }
}
