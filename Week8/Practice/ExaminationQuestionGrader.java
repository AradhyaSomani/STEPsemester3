import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Grades different question types through polymorphism. */
public class ExaminationQuestionGrader {

    abstract static class Question {
        protected final String correctAnswer;
        protected final String studentAnswer;
        protected final int points;
        Question(String correctAnswer, String studentAnswer, int points) {
            this.correctAnswer = correctAnswer;
            this.studentAnswer = studentAnswer;
            this.points = points;
        }
        abstract String getType();
        abstract double evaluate();
    }

    static class McqQuestion extends Question {
        McqQuestion(String c, String s, int p) { super(c, s, p); }
        String getType() { return "MCQ"; }
        double evaluate() { return studentAnswer.equals(correctAnswer) ? points : 0; }
    }

    static class TrueFalseQuestion extends Question {
        TrueFalseQuestion(String c, String s, int p) { super(c, s, p); }
        String getType() { return "TF"; }
        double evaluate() { return studentAnswer.equals(correctAnswer) ? points : 0; }
    }

    static class EssayQuestion extends Question {
        EssayQuestion(String c, String s, int p) { super(c, s, p); }
        String getType() { return "ESSAY"; }
        double evaluate() {
            int matched = 0;
            String answer = studentAnswer.toLowerCase();
            for (String keyword : correctAnswer.split(",")) {
                String cleaned = keyword.trim().toLowerCase();
                if (!cleaned.isEmpty() && answer.contains(cleaned)) matched++;
            }
            if (matched >= 2) return points * 0.75;
            if (matched == 1) return points * 0.50;
            return 0;
        }
    }

    private static final Pattern QUOTED = Pattern.compile("\"([^\"]*)\"");

    static Question parseQuestion(String line) {
        String type = line.substring(0, line.indexOf(' ')).toUpperCase();
        Matcher matcher = QUOTED.matcher(line);
        String[] fields = new String[3];
        int found = 0, lastEnd = 0;
        while (matcher.find() && found < 3) {
            fields[found++] = matcher.group(1);
            lastEnd = matcher.end();
        }
        if (found < 3) throw new IllegalArgumentException("Expected three quoted fields");
        int points = Integer.parseInt(line.substring(lastEnd).trim());
        switch (type) {
            case "MCQ": return new McqQuestion(fields[1], fields[2], points);
            case "TF": return new TrueFalseQuestion(fields[1], fields[2], points);
            case "ESSAY": return new EssayQuestion(fields[1], fields[2], points);
            default: throw new IllegalArgumentException("Unknown question type: " + type);
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        double total = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            int count = Integer.parseInt(scanner.nextLine().trim());
            for (int i = 0; i < count; i++) {
                String line = scanner.nextLine().trim();
                try {
                    Question question = parseQuestion(line);
                    double score = question.evaluate();
                    total += score;
                    System.out.printf("%s: %.2f%n", question.getType(), score);
                } catch (IllegalArgumentException | StringIndexOutOfBoundsException e) {
                    System.out.println("Error: invalid line '" + line + "' (" + e.getMessage() + ")");
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Error: invalid input (" + e.getMessage() + ")");
            return;
        }
        System.out.printf("Total Score: %.2f%n", total);
    }
}
