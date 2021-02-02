public class Main {
    public static void main(String[] args) {
        boolean move;
        boolean[][] board = Castle.start(6);
        double temprature = 10;
        int fitness = Castle.evaluate(board);
        if (fitness == 0) {
            Castle.appendMessage("Solution Found!");
            Castle.appendBoard(board);
            return;
        }
        Castle.appendMessage("Initial State:");
        Castle.appendBoard(board);
        SEARCH:
        while (true) {
            for (int i = 0; i < board.length; i++) {
                move = false;
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j]) {
                        if (j < board[i].length) j++;
                        else break;
                    }
                    boolean[][] successor = Castle.makeChild(board);
                    int successorFitness = Castle.evaluate(successor);
                    if (successorFitness == 0) {
                        Castle.appendMessage("Solution Found!");
                        Castle.appendBoard(successor);
                        break SEARCH;
                    }
                    if (successorFitness < fitness) {
                        Castle.appendMessage("My Child is Fitter Than Me! F=" + successorFitness);
                        Castle.appendBoard(successor);
                        fitness = successorFitness;
                        board = successor;
                        i = 0;
                        move = true;
                    } else {
                        double exponential = fitness - successorFitness - 0.1;
                        double index = Math.exp(exponential / temprature);
                        double randomNumber = Castle.rollTheDice();
                        if (randomNumber < index) {
                            Castle.appendMessage("My Child is Trash! F=" + successorFitness + " But Anyway...\nChances were: " + (float) (index * 100) + "%");
                            Castle.appendBoard(successor);
                            fitness = successorFitness;
                            board = successor;
                            i = 0;
                            move = true;
                        } else {
                            Castle.appendMessage("My Child is Trash! F=" + successorFitness + " Didn't Evolve! \nChances were: " + (float) (index * 100) + "%");
                            Castle.appendBoard(successor);
                        }
                    }
                    temprature -= 0.01;
                    if (temprature == 0) {
                        Castle.appendMessage("Board Froze!");
                        Castle.appendBoard(board);
                        break SEARCH;
                    }
                    if (move) break;
                }
            }
        }
        Castle.printHistory();
    }
}