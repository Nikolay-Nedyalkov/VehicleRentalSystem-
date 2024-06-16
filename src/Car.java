public class Car extends Vehicle {
    private int rating;

    public Car(String brand, String model, double value, int rating) {
        super(brand, model, value);
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public void printVehicleInfo() {
        System.out.printf("%s %s $%.2f Safety Rating: %d\n", getBrand(), getModel(), getValue(), rating);
    }
}
