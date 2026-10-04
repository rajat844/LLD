package Code.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class Agent {
    private UUID id;
    private String name;

    private HashSet<Expertise> expertises;
    private List<Ticket> assignedTickets;

    public Agent(UUID id, String name) {
        this.id = id;
        this.name = name;
        this.expertises = new HashSet<>();
        this.assignedTickets = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public HashSet<Expertise> getExpertises() {
        return new HashSet<>(expertises);
    }

    public void addExpertise(Expertise expertise) {
        expertises.add(expertise);
    }

    public void removeExpertise(Expertise expertise) {
        expertises.remove(expertise);
    }

    public void getDetails() {
        System.out.println("Agent " + name + " with id " + id);
    }

    public int getAssignedTicketsCount() {
        return assignedTickets.size();
    }

    public List<Ticket> getAssignedTickets() {
        return new ArrayList<>(assignedTickets);
    }

    public boolean assign(Ticket ticket) {
        if (assignedTickets.contains(ticket)) {
            return false;
        }

        assignedTickets.add(ticket);
        ticket.assignTo(this);
        ticket.startProgress();

        return true;

    }

    public boolean remove(Ticket ticket) {
        if (!assignedTickets.contains(ticket)) {
            return false;
        }

        assignedTickets.remove(ticket);
        ticket.removeAgent();
        return true;
    }

}
