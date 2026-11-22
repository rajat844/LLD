abstract class CoffeeDecorator implements Coffee {
    final Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        if (coffee == null)
            throw new IllegalArgumentException("Wrapped Coffee cannot be null");
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription();
    }

    @Override
    public int cost() {
        return coffee.cost();
    }
}
