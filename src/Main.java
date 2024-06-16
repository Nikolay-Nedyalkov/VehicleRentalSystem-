import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Hello! Thanks for choosing our services!\nWhat are we gonna be driving today? :D");

        List<Car> cars = GetCars();
        List<Motorcycle> motorcycles = GetMotorcycles();
        List<CargoVan> cargoVans = GetCargoVans();

        String vehicleType = GetValidVehicleType();

        switch (vehicleType) {
            case "car":
                PrintCars(cars);
                int selectedCarIndex = GetSelectedVehicleIndex(cars.size(), vehicleType);
                Car selectedCar = cars.get(selectedCarIndex);
                System.out.print("Selected: ");
                selectedCar.printVehicleInfo();
                RentVehicle(selectedCar, vehicleType);
                break;
            case "motorcycle":
                PrintMotorcycles(motorcycles);
                int selectedMotorcycleIndex = GetSelectedVehicleIndex(motorcycles.size(), vehicleType);
                Motorcycle selectedMotorcycle = motorcycles.get(selectedMotorcycleIndex);
                System.out.print("Selected: ");
                selectedMotorcycle.printVehicleInfo();
                int riderAge = GetValidRiderAge();
                selectedMotorcycle.setRiderAge(riderAge);
                RentVehicle(selectedMotorcycle, vehicleType);
                break;
            case "cargo van":
                PrintCargoVans(cargoVans);
                int selectedCargoVanIndex = GetSelectedVehicleIndex(cargoVans.size(), vehicleType);
                CargoVan selectedCargoVan = cargoVans.get(selectedCargoVanIndex);
                System.out.print("Selected: ");
                selectedCargoVan.printVehicleInfo();
                int yearsOfExperience = GetValidInteger("Years of driving experience: ");
                selectedCargoVan.setYearsOfExperience(yearsOfExperience);
                RentVehicle(selectedCargoVan, vehicleType);
                break;
            default:
                break;
        }

        scanner.close();
    }

    private static List<Car> GetCars() {
        return List.of(
                new Car("Volvo", "V70", 6000, 5),
                new Car("Honda", "Accord", 10000, 4),
                new Car("Ford", "Fusion", 15000, 3),
                new Car("Toyota", "Camry", 12000, 2),
                new Car("BMW", "316ti", 3000, 1),
                new Car("Opel", "Corsa", 1000, 3),
                new Car("BMW", "M340i", 120000, 3)
        );
    }

    private static List<Motorcycle> GetMotorcycles() {
        return List.of(
                new Motorcycle("Yamaha", "YZF-R3", 5000),
                new Motorcycle("Kawasaki", "Ninja 300", 6000),
                new Motorcycle("Honda", "CBR500R", 7000),
                new Motorcycle("Yamaha", "R1", 18000),
                new Motorcycle("BMW", "S1000rr", 25000),
                new Motorcycle("Husqvarna", "FE 501", 10000)
        );
    }

    private static List<CargoVan> GetCargoVans() {
        return List.of(
                new CargoVan("Ford", "Transit", 25000),
                new CargoVan("Mercedes", "Sprinter", 30000),
                new CargoVan("Ram", "ProMaster", 28000),
                new CargoVan("Chevrolet", "Express", 20000),
                new CargoVan("Renault", "Master", 15000),
                new CargoVan("Opel", "Combo Cargo", 10000)
        );
    }

    private static String GetValidVehicleType() {
        while (true) {
            System.out.println("Car, motorcycle or cargo van?:");
            String vehicleType = scanner.nextLine().trim().toLowerCase();

            if (vehicleType.equals("car") || vehicleType.equals("motorcycle") || vehicleType.equals("cargo van")) {
                return vehicleType;
            }

            System.out.println("Invalid input. Please enter 'car', 'motorcycle', or 'cargo van'.");
        }
    }

    private static void PrintCars(List<Car> cars) {
        int counter = 1;
        for (Car car : cars) {
            System.out.print(counter++ + ". ");
            car.printVehicleInfo();
        }
    }

    private static void PrintMotorcycles(List<Motorcycle> motorcycles) {
        int counter = 1;
        for (Motorcycle motorcycle : motorcycles) {
            System.out.print(counter++ + ". ");
            motorcycle.printVehicleInfo();
        }
    }

    private static void PrintCargoVans(List<CargoVan> cargoVans) {
        int counter = 1;
        for (CargoVan van : cargoVans) {
            System.out.print(counter++ + ". ");
            van.printVehicleInfo();
        }
    }

    private static int GetSelectedVehicleIndex(int count, String vehicleType) {
        while (true) {
            System.out.println("Select a " + vehicleType + " by number:");
            try {
                int index = Integer.parseInt(scanner.nextLine().trim());
                if (index > 0 && index <= count) {
                    return index - 1;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please select a valid number from the list.");
            }
        }
    }

    private static int GetValidRiderAge() {
        while (true) {
            System.out.println("Rider age: ");
            try {
                int riderAge = Integer.parseInt(scanner.nextLine().trim());
                if (riderAge >= 18) {
                    return riderAge;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Rider should be at least 18 years old.");
            }
        }
    }

    private static void RentVehicle(Vehicle vehicle, String vehicleType) {
        String customerName = GetValidInput("Enter your name: ");

        System.out.println("Start date for the rental (yyyy-mm-dd):");
        LocalDate startDate = getValidDate();

        int rentalDays = GetValidInteger("Enter the number of rental days: ");

        LocalDate endDate = startDate.plusDays(rentalDays);

        System.out.println("Return date for the rental (yyyy-mm-dd):      //simulates a real life scenario when the car is returned");
        LocalDate returnDate = getValidReturnDate(startDate, endDate);

        Invoice invoice = new Invoice(customerName, vehicle, startDate, endDate, returnDate);
        invoice.printInvoice();
    }

    private static String GetValidInput(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    private static int GetValidInteger(String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value > 0) {
                    return value;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a positive integer.");
            }
        }
    }

    private static LocalDate getValidDate() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-mm-dd format:");
            }
        }
    }

    private static LocalDate getValidReturnDate(LocalDate startDate, LocalDate endDate) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                LocalDate returnDate = LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
                if ((returnDate.isAfter(startDate) && returnDate.isBefore(endDate)) || returnDate.isEqual(endDate)) {
                    return returnDate;
                } else {
                    System.out.println("Invalid return date. Please enter a date within the rental period:");
                }
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-mm-dd format:");
            }
        }
    }
}