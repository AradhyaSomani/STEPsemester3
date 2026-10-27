import java.util.Locale;
import java.util.Scanner;

/** Parcels: shipping is abstract-class behaviour; insurance is an optional Insurable capability. */
public class ParcelShippingDesk {

    interface Insurable {
        double INSURANCE_RATE = 0.02;
        double calculateInsurance();
    }

    abstract static class Parcel {
        protected final double weightKg;
        protected final double declaredValue;
        Parcel(double weightKg, double declaredValue) {
            if (weightKg <= 0 || declaredValue < 0) throw new IllegalArgumentException("invalid weight or value");
            this.weightKg = weightKg;
            this.declaredValue = declaredValue;
        }
        abstract String getType();
        abstract double calculateCharge();
        double getInsurance() { return this instanceof Insurable ? ((Insurable) this).calculateInsurance() : 0.0; }
    }

    static class StandardParcel extends Parcel {
        StandardParcel(double w, double v) { super(w, v); }
        String getType() { return "STANDARD"; }
        double calculateCharge() { return 40.0 + 10.0 * weightKg; }
    }

    static class ExpressParcel extends Parcel implements Insurable {
        ExpressParcel(double w, double v) { super(w, v); }
        String getType() { return "EXPRESS"; }
        double calculateCharge() { return 80.0 + 15.0 * weightKg; }
        public double calculateInsurance() { return declaredValue * INSURANCE_RATE; }
    }

    static class FragileParcel extends Parcel implements Insurable {
        private static final double HANDLING_FEE = 50.0;
        FragileParcel(double w, double v) { super(w, v); }
        String getType() { return "FRAGILE"; }
        double calculateCharge() { return 40.0 + 10.0 * weightKg + HANDLING_FEE; }
        public double calculateInsurance() { return declaredValue * INSURANCE_RATE; }
    }

    static Parcel createParcel(String[] p) {
        double weight = Double.parseDouble(p[1]);
        double value = Double.parseDouble(p[2]);
        switch (p[0].toUpperCase()) {
            case "STANDARD": return new StandardParcel(weight, value);
            case "EXPRESS": return new ExpressParcel(weight, value);
            case "FRAGILE": return new FragileParcel(weight, value);
            default: throw new IllegalArgumentException("Unknown parcel type: " + p[0]);
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        double grandTotal = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            int count = Integer.parseInt(scanner.nextLine().trim());
            for (int i = 0; i < count; i++) {
                String[] parts = scanner.nextLine().trim().split("\\s+");
                try {
                    Parcel parcel = createParcel(parts);
                    double charge = parcel.calculateCharge();
                    double insurance = parcel.getInsurance();
                    grandTotal += charge + insurance;
                    System.out.printf("%s: Charge=%.2f Insurance=%.2f Total=%.2f%n",
                            parcel.getType(), charge, insurance, charge + insurance);
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: invalid line '" + String.join(" ", parts) + "'");
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Error: invalid input (" + e.getMessage() + ")");
            return;
        }
        System.out.printf("Grand Total: %.2f%n", grandTotal);
    }
}
