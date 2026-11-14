package code.model;

public enum Expertise {
    SOFTWARE,
    QUALITY_ANALYSIS,
    PRODUCT,
    HUMAN_RESOURCE,
    HUMAN_RELATIONS;

    public static Expertise forTicketType(TicketType ticketType) {
        if (ticketType == null) {
            return null;
        }
        switch (ticketType) {
            case SOFTWARE:
                return SOFTWARE;
            case QUALITY_ANALYSIS:
                return QUALITY_ANALYSIS;
            case PRODUCT:
                return PRODUCT;
            case HUMAN_RESOURCE:
            case HUMAN_RELATIONS:
                return HUMAN_RESOURCE;
            default:
                try {
                    return Expertise.valueOf(ticketType.name());
                } catch (IllegalArgumentException e) {
                    return null;
                }
        }
    }
}
