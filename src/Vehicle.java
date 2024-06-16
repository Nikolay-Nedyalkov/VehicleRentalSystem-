public abstract class Vehicle {
    private String brand;
    private String model;
    private double value;

    public Vehicle(String brand, String model, double value) {
        this.brand = brand;
        this.model = model;
        this.value = value;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void printVehicleInfo() {
        System.out.printf("%s %s %.2f%n", brand, model, value);
    }
}
