package code.entities;

import java.util.UUID;

public class Bill {
    private UUID id;
    private int amount;

    public Bill(int amount) {
        this.amount = amount;
    }    
}
