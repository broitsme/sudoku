package sudoku;

public class driver {
    public static void main(String[] args) {
        int arr[][] = { {9, 0, 0, 0, 2, 0, 7, 5, 0},
                        {6, 0, 0, 0, 5, 0, 0, 4, 0},
                        {0, 2, 0, 4, 0, 0, 0, 1, 0},
                        {2, 0, 8, 0, 0, 0, 0, 0, 0},
                        {0, 7, 0, 5, 0, 9, 0, 6, 0},
                        {0, 0, 0, 0, 0, 0, 4, 0, 1},
                        {0, 1, 0, 0, 0, 5, 0, 8, 0},
                        {0, 9, 0, 0, 7, 0, 0, 0, 4},
                        {0, 8, 2, 0, 4, 0, 0, 0, 6}};
        board newBoard = new board(arr);
        newBoard.solveSudoku();
    }
}
