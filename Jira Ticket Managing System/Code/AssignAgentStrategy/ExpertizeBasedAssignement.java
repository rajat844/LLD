package Code.AssignAgentStrategy;

import java.util.List;
import java.util.Optional;

import Code.Model.Agent;
import Code.Model.Expertise;
import Code.Model.TicketType;

public class ExpertizeBasedAssignement implements AssignAgentStartegy {
    @Override
    public Optional<Agent> getAgent(List<Agent> agents, TicketType ticketType) {
        Expertise requiredExpertise = Expertise.forTicketType(ticketType);

        int minimumTickets = Integer.MAX_VALUE;
        Agent selectedAgent = null;

        for (Agent agent : agents) {
            if (!agent.getExpertises().contains(requiredExpertise)) {
                continue;
            }

            int currentLoad = agent.getAssignedTicketsCount();
            if(currentLoad < minimumTickets) {
                selectedAgent = agent;
                minimumTickets = currentLoad;
            }
        }

        return Optional.ofNullable(selectedAgent);
    }

}
