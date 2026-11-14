package code.strategy;

import java.util.List;
import java.util.Optional;

import code.model.Agent;
import code.model.Ticket;
import code.model.TicketType;

public interface AssignAgentStrategy {
    Optional<Agent> getAgent(List<Agent> agents, TicketType ticketType);

    default Optional<Agent> getAgent(List<Agent> agents, Ticket ticket) {
        if (ticket == null) {
            return Optional.empty();
        }
        return getAgent(agents, ticket.getType());
    }
}
