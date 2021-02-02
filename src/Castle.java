import java.util.Random;

public class Castle {

    private static Random random = new Random();
    private static String history = "";

    static boolean[][] start(int length) {
        boolean[][] board = new boolean[length][length];
        for (int i = 0; i < board.length; i++) {
            int a = random.nextInt(board.length);
            board[i][a] = true;
        }
        return board;
    }

    static int evaluate(boolean[][] board) {
        int result = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j]) {
                    for (int k = 0; k < board.length; k++) {
                        for (int l = 0; l < board[i].length; l++) {
                            if (board[k][l]) {
                                if (k == i && j == l) continue;
                                if (k == i || j == l || Math.abs(k - i) == Math.abs(l - j))
                                    result++;
                            }
                        }
                    }
                }
            }
        }
        return result / 2;
    }

    static boolean[][] makeChild(boolean[][] board) {
        boolean[][] successor = board.clone();
        int random_i = random.nextInt(board.length);
        int random_j = random.nextInt(board.length);
        for (int _j = 0; _j < board[random_i].length; _j++) {
            successor[random_i][_j] = false;
        }
        successor[random_i][random_j] = true;
        return successor;
    }

    static void appendBoard(boolean[][] board) {
        for (boolean[] aBoard : board) {
            for (int l = 0; l < aBoard.length; l++) {
                history += aBoard[l] ? "0   " : "-   ";
            }
            history += "\n";
        }
        history += "********************\n";
    }

    static void appendMessage(String message) {
        history += message + "\n";
    }

    static double rollTheDice() {
        return random.nextDouble();
    }

    static void printHistory() {
        System.out.println(history.trim());
    }
}
