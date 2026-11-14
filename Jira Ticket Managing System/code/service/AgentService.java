package code.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import code.model.Agent;
import code.model.Ticket;
import code.repository.AgentRepository;
import code.strategy.AssignAgentStrategy;

public class AgentService {
    private final AgentRepository agentRepository;
    private final AssignAgentStrategy assignmentStrategy;

    public AgentService(AgentRepository agentRepository, AssignAgentStrategy assignmentStrategy) {
        this.agentRepository = agentRepository;
        this.assignmentStrategy = assignmentStrategy;
    }

    public void addAgent(Agent agent) {
        agentRepository.addAgent(agent);
    }

    public Optional<Agent> getAgent(UUID agentId) {
        return agentRepository.findById(agentId);
    }

    public List<Agent> getAllAgents() {
        return agentRepository.getAllAgents();
    }

    public boolean assignTicket(Ticket ticket) {
        if (ticket == null) {
            return false;
        }

        Optional<Agent> agent = assignmentStrategy.getAgent(agentRepository.getAllAgents(), ticket.getType());
        if (agent.isEmpty()) {
            return false;
        }

        return agent.get().assign(ticket);
    }

    public boolean changeAgent(Ticket ticket, UUID newAgentId) {
        if (ticket == null || newAgentId == null) {
            return false;
        }

        Optional<Agent> newAgent = agentRepository.findById(newAgentId);
        if (newAgent.isEmpty()) {
            return false;
        }

        Agent oldAgent = ticket.getAssignedAgent();
        if (oldAgent != null) {
            oldAgent.remove(ticket);
        }

        return newAgent.get().assign(ticket);
    }
}
