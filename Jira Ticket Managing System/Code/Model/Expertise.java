package Code.Model;

public enum Expertise {

    SOFTWARE, QUALITY_ANALYSIS, PRODUCT, HUMAN_RELATIONS;

    public static Expertise forTicketType(TicketType ticketType) {
        return Expertise.valueOf(ticketType.name());
    }
}