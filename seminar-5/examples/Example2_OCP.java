package examples;

public class Example2_OCP {

    static class Circle {
        double radius;

        public Circle(double radius) {
            this.radius = radius;
        }
    }

    static class Rectangle {
        double width;
        double height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }
    }

    static class Triangle {
        double base;
        double height;

        public Triangle(double base, double height) {
            this.base = base;
            this.height = height;
        }
    }

    static class AreaCalculator {
        public double calculateArea(Object shape) {
            if (shape instanceof Circle) {
                Circle circle = (Circle) shape;
                return Math.PI * circle.radius * circle.radius;

            } else if (shape instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) shape;
                return rectangle.width * rectangle.height;

            } else if (shape instanceof Triangle) {
                Triangle triangle = (Triangle) shape;
                return 0.5 * triangle.base * triangle.height;

            } else {
                throw new IllegalArgumentException("Неизвестная фигура!");
            }

            // Что если нужно добавить Pentagon (пятиугольник)?
            // Придется ИЗМЕНЯТЬ этот метод!
            // Нарушение OCP!
        }
    }

    public static void main(String[] args) {
        AreaCalculator calculator = new AreaCalculator();

        Circle circle = new Circle(5);
        Rectangle rectangle = new Rectangle(4, 6);
        Triangle triangle = new Triangle(3, 4);

        System.out.println("Площадь круга: " + calculator.calculateArea(circle));
        System.out.println("Площадь прямоугольника: " + calculator.calculateArea(rectangle));
        System.out.println("Площадь треугольника: " + calculator.calculateArea(triangle));

        // Проблемы:
        // 1. При добавлении новой фигуры нужно менять calculateArea
        // 2. Метод становится все больше и сложнее
        // 3. Высокий риск внести баг при изменении
    }
}
