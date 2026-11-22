public class OlcChargerAdapter implements Charger {
    private final OldCharger oldCharger;

    public OlcChargerAdapter(OldCharger oldCharger) {
        this.oldCharger = oldCharger;
    }

    public void charge() {
        oldCharger.oldCharge();
    }

}
