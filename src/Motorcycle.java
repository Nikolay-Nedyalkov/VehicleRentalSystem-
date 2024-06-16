public class Motorcycle extends Vehicle {
    private int riderAge;

    public Motorcycle(String brand, String model, double value) {
        super(brand, model, value);
    }

    public int getRiderAge() {
        return riderAge;
    }

    public void setRiderAge(int riderAge) {
        this.riderAge = riderAge;
    }
}
