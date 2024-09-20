import java.util.*;
public class Main {
    // Main method to start the game
    public static void main(String[] args) {
        // Create a Minesweeper game with specific dimensions and number of mines
        Minesweeper game = new Minesweeper(10, 10, 10);

        // Game loop
        Scanner scnr = new Scanner(System.in);
        while (!game.getGameOver()) {
            game.displayBoard();
            // Get player input for row, col, and action (reveal or flag)
            // For now, just simulate a move (to be replaced with real player input logic)
            System.out.println("Enter desired row: ");
            int row = scnr.nextInt();
            System.out.println("Enter desired column:");
            int col = scnr.nextInt();

            System.out.println();
            System.out.println("Enter Action: reveal/flag/unflag ");
            String action = scnr.next();


            game.playerMove(row,col,action);

            // Check for win or loss conditions
            if (game.checkWin()) {
                System.out.println("Congratulations! You've won the game.");
                break;
            }
            if (game.checkLoss(0, 0)) {
                System.out.println("Game Over! You hit a mine.");
                game.setGameOver(true);
            }
        }
    }
}