package Code.AssignAgentStrategy;

import java.util.List;
import java.util.Optional;

import Code.Model.Agent;
import Code.Model.TicketType;

public interface AssignAgentStartegy {
    public Optional<Agent> getAgent(List<Agent> agents, TicketType ticketType);
}