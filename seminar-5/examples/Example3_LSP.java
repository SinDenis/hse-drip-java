package examples;

public class Example3_LSP {

    static class Car {
        protected String model;
        protected double fuelLevel;

        public Car(String model) {
            this.model = model;
            this.fuelLevel = 0;
        }

        public void refuel(double liters) {
            fuelLevel += liters;
            System.out.println(model + " заправлен. Топливо: " + fuelLevel + " л");
        }

        public void drive() {
            if (fuelLevel > 0) {
                System.out.println(model + " едет");
                fuelLevel -= 5;
            } else {
                System.out.println(model + " не может ехать - нет топлива!");
            }
        }

        public String getModel() {
            return model;
        }
    }

    // Электромобиль - это машина, но заправляется не так!
    static class ElectricCar extends Car {
        private double batteryLevel;

        public ElectricCar(String model) {
            super(model);
            this.batteryLevel = 0;
        }

        @Override
        public void refuel(double liters) {
            // Электромобили не заправляются бензином!
            throw new UnsupportedOperationException(
                model + " - электромобиль! Используйте charge() вместо refuel()!"
            );
        }

        public void charge(double kwh) {
            batteryLevel += kwh;
            System.out.println(model + " заряжен. Батарея: " + batteryLevel + " кВт⋅ч");
        }

        @Override
        public void drive() {
            if (batteryLevel > 0) {
                System.out.println(model + " едет (на электричестве)");
                batteryLevel -= 2;
            } else {
                System.out.println(model + " не может ехать - батарея разряжена!");
            }
        }
    }

    // Метод для заправки любого автомобиля
    static void refuelAndDrive(Car car) {
        System.out.println("\n--- Заправка автомобиля ---");
        car.refuel(10);  // Ожидаем, что любая машина может заправиться
        car.drive();
    }

    public static void main(String[] args) {
        Car toyota = new Car("Toyota Camry");
        Car tesla = new ElectricCar("Tesla Model 3");

        System.out.println("=== Бензиновый автомобиль ===");
        refuelAndDrive(toyota);  // Работает

        System.out.println("\n=== Электромобиль ===");
        try {
            refuelAndDrive(tesla);  // НЕ работает! Нарушение LSP!
        } catch (UnsupportedOperationException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Проблема: ElectricCar не может заменить Car без нарушения логики!
    }
}
