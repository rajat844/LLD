public class Phone {
    private final Charger charger;

    public Phone(Charger charger) {
        this.charger = charger;
    }

    public void plugIn() {
        charger.charge();
    }
}
