import java.util.Random;
import java.util.Scanner;

/** Rock-Paper-Scissors simulator: player vs computer over N rounds. */
public class RockPaperScissorsGame {

    private static final int TOTAL_ROUNDS = 5;
    private static final String[] MOVES = {"Rock", "Paper", "Scissors"};
    private static final String PLAYER_WINS = "Player Wins";
    private static final String COMPUTER_WINS = "Computer Wins";
    private static final String DRAW = "Draw";

    public static String generateComputerMove(Random random) {
        return MOVES[random.nextInt(MOVES.length)];
    }

    /** Converts user text (any case, or R/P/S) to a valid move; throws for bad input. */
    public static String normalizeMove(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Move cannot be empty.");
        }
        String cleaned = input.trim();
        for (String move : MOVES) {
            if (move.equalsIgnoreCase(cleaned)
                    || move.substring(0, 1).equalsIgnoreCase(cleaned)) {
                return move;
            }
        }
        throw new IllegalArgumentException("Invalid move: '" + input + "'. Use Rock, Paper or Scissors.");
    }

    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove)) {
            return DRAW;
        }
        boolean playerWins = (playerMove.equals("Rock") && computerMove.equals("Scissors"))
                || (playerMove.equals("Paper") && computerMove.equals("Rock"))
                || (playerMove.equals("Scissors") && computerMove.equals("Paper"));
        return playerWins ? PLAYER_WINS : COMPUTER_WINS;
    }

    public static String readPlayerMove(Scanner scanner, int round) {
        while (true) {
            System.out.print("Round " + round + " - Enter Rock/Paper/Scissors: ");
            try {
                return normalizeMove(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("  " + e.getMessage());
            }
        }
    }

    public static double calculateWinPercentage(int wins, int totalRounds) {
        return totalRounds == 0 ? 0.0 : (wins * 100.0) / totalRounds;
    }

    public static void printSummary(String[] player, String[] computer, String[] results) {
        System.out.println("\nRound | Player Move | Computer Move | Result");
        System.out.println("------+-------------+---------------+--------------");
        int wins = 0, losses = 0, draws = 0;
        for (int i = 0; i < results.length; i++) {
            System.out.printf("%-5d | %-11s | %-13s | %s%n", i + 1, player[i], computer[i], results[i]);
            if (results[i].equals(PLAYER_WINS)) wins++;
            else if (results[i].equals(COMPUTER_WINS)) losses++;
            else draws++;
        }
        System.out.printf("%nWins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n",
                wins, losses, draws, calculateWinPercentage(wins, results.length));
    }

    public static void main(String[] args) {
        Random random = new Random();
        String[] playerMoves = new String[TOTAL_ROUNDS];
        String[] computerMoves = new String[TOTAL_ROUNDS];
        String[] results = new String[TOTAL_ROUNDS];

        try (Scanner scanner = new Scanner(System.in)) {
            for (int i = 0; i < TOTAL_ROUNDS; i++) {
                playerMoves[i] = readPlayerMove(scanner, i + 1);
                computerMoves[i] = generateComputerMove(random);
                results[i] = playRound(playerMoves[i], computerMoves[i]);
                System.out.println("  Computer chose " + computerMoves[i] + " -> " + results[i]);
            }
        }
        printSummary(playerMoves, computerMoves, results);
    }
}
