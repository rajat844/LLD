package Code.Service;

import java.util.Optional;
import java.util.UUID;

import Code.AssignAgentStrategy.AssignAgentStartegy;
import Code.Model.Agent;
import Code.Model.Ticket;
import Code.Repository.AgentRepository;

public class AgentService {
    private final AgentRepository agentRepository;
    private final AssignAgentStartegy assignmentStrategy;

    public AgentService(AgentRepository agentRepository, AssignAgentStartegy assignAgentStartegy) {
        this.agentRepository = agentRepository;
        this.assignmentStrategy = assignAgentStartegy;
    }

    public void addAgent(Agent agent) {
        agentRepository.addAgent(agent);
    }

    public Optional<Agent> getAgent(UUID agentId) {
        return agentRepository.findById(agentId);
    }

    public boolean assignTicket(Ticket ticket) {
        Optional<Agent> agent = assignmentStrategy.getAgent(agentRepository.getAllAgents(), ticket.getType());

        if (agent.isEmpty())
            return false;

        return agent.get().assign(ticket);
    }

    public boolean changeAgent(Ticket ticket, UUID newAgentId) {
        Optional<Agent> newAgent = agentRepository.findById(newAgentId);

        if (newAgent.isEmpty())
            return false;

        Agent oldAgent = ticket.getAssignedAgent();

        if (oldAgent != null) {
            oldAgent.remove(ticket);
        }
        return newAgent.get().assign(ticket);
    }

}
