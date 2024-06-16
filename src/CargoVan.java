public class CargoVan extends Vehicle {
    private int yearsOfExperience;

    public CargoVan(String brand, String model, double value) {
        super(brand, model, value);
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
