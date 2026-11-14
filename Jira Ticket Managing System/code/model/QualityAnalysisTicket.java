package code.model;

public class QualityAnalysisTicket extends CompositeTicket {

    public QualityAnalysisTicket(String name, String description) {
        super(name, description, TicketType.QUALITY_ANALYSIS);
    }

    public QualityAnalysisTicket(String name) {
        this(name, "");
    }
}
