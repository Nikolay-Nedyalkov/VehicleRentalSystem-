import java.time.LocalDate;

public class Invoice {
    private String customerName;
    private Vehicle rentedVehicle;
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;
    private LocalDate actualReturnDate;

    public Invoice(String customerName, Vehicle rentedVehicle, LocalDate reservationStartDate,
                   LocalDate reservationEndDate, LocalDate actualReturnDate) {
        this.customerName = customerName;
        this.rentedVehicle = rentedVehicle;
        this.reservationStartDate = reservationStartDate;
        this.reservationEndDate = reservationEndDate;
        this.actualReturnDate = actualReturnDate;
    }

    public void printInvoice() {
        System.out.println();
        System.out.println("XXXXXXXXXX");
        System.out.println("Date: " + LocalDate.now().toString());
        System.out.println("Customer Name: " + customerName);
        System.out.println("Rented Vehicle: " + rentedVehicle.getBrand() + " " + rentedVehicle.getModel());
        System.out.println();
        System.out.println("Reservation start date: " + reservationStartDate.toString());
        System.out.println("Reservation end date: " + reservationEndDate.toString());
        System.out.println("Reserved rental days: " + getReservedRentalDays() + " days");
        System.out.println();
        System.out.println("Actual Return date: " + actualReturnDate.toString());
        System.out.println("Actual rental days: " + getActualRentalDays() + " days");
        System.out.println();
        double rentalCostPerDay = calculateDailyRent();
        double insurancePerDay = calculateInitialDailyInsurance();
        System.out.printf("Rental cost per day: $%.2f%n", rentalCostPerDay);

        if (rentedVehicle instanceof Car && ((Car) rentedVehicle).getRating() > 3) {
            System.out.printf("Initial insurance per day: $%.2f%n", insurancePerDay);
            System.out.printf("Insurance discount per day: $%.2f%n", insurancePerDay * 0.1);
            insurancePerDay *= 0.9;
        }
        if (rentedVehicle instanceof Motorcycle && ((Motorcycle) rentedVehicle).getRiderAge() < 25) {
            System.out.printf("Initial insurance per day: $%.2f%n", insurancePerDay);
            System.out.printf("Insurance addition per day: $%.2f%n", insurancePerDay * 0.2);
            insurancePerDay *= 1.2;
        }
        if (rentedVehicle instanceof CargoVan && ((CargoVan) rentedVehicle).getYearsOfExperience() > 5) {
            System.out.printf("Initial insurance per day: $%.2f%n", insurancePerDay);
            System.out.printf("Insurance discount per day: $%.2f%n", insurancePerDay * 0.15);
            insurancePerDay *= 0.85;
        }
        System.out.printf("Insurance per day: $%.2f%n", insurancePerDay);
        System.out.println();
        if (getReservedRentalDays() > getActualRentalDays()) {
            System.out.printf("Early return discount for rent: $%.2f%n",
                    (getReservedRentalDays() - getActualRentalDays()) * rentalCostPerDay * 0.5);
            System.out.printf("Early return discount for insurance: $%.2f%n",
                    (getReservedRentalDays() - getActualRentalDays()) * insurancePerDay);
            System.out.println();
        }
        double totalInsurance = getActualRentalDays() * insurancePerDay;
        double totalRent = calculateTotalRent(rentalCostPerDay);
        System.out.printf("Total rent: $%.2f%n", totalRent);
        System.out.printf("Total insurance: $%.2f%n", totalInsurance);
        System.out.printf("Total: $%.2f%n", totalRent + totalInsurance);
        System.out.println("XXXXXXXXXX");
    }

    private int getReservedRentalDays() {
        return reservationEndDate.compareTo(reservationStartDate);
    }

    private int getActualRentalDays() {
        return actualReturnDate.compareTo(reservationStartDate);
    }

    private double calculateDailyRent() {
        if (getActualRentalDays() <= 7) {
            if (rentedVehicle instanceof Car)
                return 20;
            else if (rentedVehicle instanceof Motorcycle)
                return 15;
            else
                return 50;
        } else {
            if (rentedVehicle instanceof Car)
                return 15;
            else if (rentedVehicle instanceof Motorcycle)
                return 10;
            else
                return 40;
        }
    }

    private double calculateInitialDailyInsurance() {
        if (rentedVehicle instanceof Car)
            return rentedVehicle.getValue() * 0.01 / 100;
        else if (rentedVehicle instanceof Motorcycle)
            return rentedVehicle.getValue() * 0.02 / 100;
        else
            return rentedVehicle.getValue() * 0.03 / 100;
    }

    private double calculateTotalRent(double rentalCostPerDay) {
        if (getActualRentalDays() < getReservedRentalDays())
            return getActualRentalDays() * rentalCostPerDay +
                    (getReservedRentalDays() - getActualRentalDays()) * rentalCostPerDay * 0.5;
        else
            return getReservedRentalDays() * rentalCostPerDay;
    }
}
