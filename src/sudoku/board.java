package sudoku;

import java.util.Scanner;

public class board {
    public board(int[][] board){
        this.board = board;
    }
    public board(){}
    private int[][] board;
    public void solveSudoku(){
        if(board == null) {
            getInput();
        }
        int[][] solutionBoard = solution.sudokuSolver(board);
        printSudokuSolution(solutionBoard);
    }
    private void getInput() {
        board = new int[9][9];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter zeros in empty places");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = scanner.nextInt();
                if (board[i][j] < 0 || board[i][j] > 9) {
                    System.out.println("Not a valid Input");
                    break;
                }
            }
        }
        scanner.close();
    }

    public void printSudokuSolution(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
