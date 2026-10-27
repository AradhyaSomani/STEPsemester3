import java.util.Locale;
import java.util.Scanner;

/** Garden plot report: each plot shape calculates its own area. */
public class GardenPlotAreaReport {

    abstract static class Plot {
        protected final String owner;
        Plot(String owner) { this.owner = owner; }
        String getOwner() { return owner; }
        abstract String getShape();
        abstract double calculateArea();
    }

    static class CirclePlot extends Plot {
        private final double radius;
        CirclePlot(String owner, double radius) { super(owner); this.radius = radius; }
        String getShape() { return "CIRCLE"; }
        double calculateArea() { return Math.PI * radius * radius; }
    }

    static class RectanglePlot extends Plot {
        private final double length, width;
        RectanglePlot(String owner, double length, double width) { super(owner); this.length = length; this.width = width; }
        String getShape() { return "RECTANGLE"; }
        double calculateArea() { return length * width; }
    }

    static class TrianglePlot extends Plot {
        private final double base, height;
        TrianglePlot(String owner, double base, double height) { super(owner); this.base = base; this.height = height; }
        String getShape() { return "TRIANGLE"; }
        double calculateArea() { return 0.5 * base * height; }
    }

    static Plot createPlot(String[] p) {
        switch (p[0].toUpperCase()) {
            case "CIRCLE": return new CirclePlot(p[1], Double.parseDouble(p[2]));
            case "RECTANGLE": return new RectanglePlot(p[1], Double.parseDouble(p[2]), Double.parseDouble(p[3]));
            case "TRIANGLE": return new TrianglePlot(p[1], Double.parseDouble(p[2]), Double.parseDouble(p[3]));
            default: throw new IllegalArgumentException("Unknown shape: " + p[0]);
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        double totalArea = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            int count = Integer.parseInt(scanner.nextLine().trim());
            for (int i = 0; i < count; i++) {
                String[] parts = scanner.nextLine().trim().split("\\s+");
                try {
                    Plot plot = createPlot(parts);
                    double area = plot.calculateArea();
                    totalArea += area;
                    System.out.printf("%s (%s): %.2f%n", plot.getOwner(), plot.getShape(), area);
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: invalid line '" + String.join(" ", parts) + "'");
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Error: invalid input (" + e.getMessage() + ")");
            return;
        }
        System.out.printf("Total Area: %.2f%n", totalArea);
    }
}
