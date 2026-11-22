package code.strategy;

import java.util.List;
import java.util.Optional;

import code.model.Agent;
import code.model.Expertise;
import code.model.TicketType;

public class ExpertizeBasedAssignment implements AssignAgentStrategy {
    @Override
    public Optional<Agent> getAgent(List<Agent> agents, TicketType ticketType) {
        if (agents == null || ticketType == null) {
            return Optional.empty();
        }

        Expertise requiredExpertise = Expertise.forTicketType(ticketType);
        if (requiredExpertise == null) {
            return Optional.empty();
        }

        int minimumTickets = Integer.MAX_VALUE;
        Agent selectedAgent = null;

        for (Agent agent : agents) {
            boolean hasExpertise = agent.getExpertises().contains(requiredExpertise);
            if (!hasExpertise && requiredExpertise == Expertise.HUMAN_RESOURCE) {
                hasExpertise = agent.getExpertises().contains(Expertise.HUMAN_RELATIONS);
            }

            if (!hasExpertise) {
                continue;
            }

            int currentLoad = agent.getAssignedTicketsCount();
            if (currentLoad < minimumTickets) {
                selectedAgent = agent;
                minimumTickets = currentLoad;
            }
        }

        return Optional.ofNullable(selectedAgent);
    }
}
