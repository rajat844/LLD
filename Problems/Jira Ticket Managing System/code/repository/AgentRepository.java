package code.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import code.model.Agent;

public class AgentRepository {
    private final List<Agent> agents;

    public AgentRepository() {
        this.agents = new ArrayList<>();
    }

    public void addAgent(Agent agent) {
        agents.add(agent);
    }

    public Optional<Agent> findById(UUID id) {
        return agents.stream()
                .filter(agent -> agent.getId().equals(id))
                .findFirst();
    }

    public List<Agent> getAllAgents() {
        return new ArrayList<>(agents);
    }
}
