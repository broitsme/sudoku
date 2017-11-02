package sudoku;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class solution {
    private static int[][] boardForProcess; //for process
    private static int[][] boardForCheck; //for checking if can keep Values
    private static int[][] solutionBoard;  // final solution board

    static int[][] sudokuSolver(int[][] sudokuBoard) {
        boardForProcess = getCopyOf(sudokuBoard);
        boardForCheck = getCopyOf(sudokuBoard);
        putValuesInMaps();
        try {
            sudokuBoardHelp(boardForProcess, 0, 0);
        } catch (SolutionCompleted ob) {
        }
        return solutionBoard;
    }

    private static void sudokuBoardHelp(int[][] board, int i, int j) throws SolutionCompleted {
        if (i == 9) {
            if(isComplete()) {
                solutionBoard = getCopyOf(board);
                throw new SolutionCompleted();
            }
            return;
        }
        if (j == 9) {
            sudokuBoardHelp(board, i + 1, 0);
            return;
        }
        for (int num = 1; num <= 9; num++) {
            if (canChange(i, j) && canPut(i, j, num)) {
                upDateMaps(i, j, num);
                board[i][j] = num;
                sudokuBoardHelp(board, i, j + 1);
                undoUpDate(i, j, num);
                board[i][j] = 0;
            } else if(!canChange(i, j)){
                sudokuBoardHelp(board, i, j + 1);
            }
        }
    }
    private static boolean isComplete(){
        for (int i = 0; i < 9; i++){
            if(valuesInCols[i].size() != 9){
                return false;
            }
        }
        return true;
    }
    static void upDateMaps(int i, int j, int val){
        valuesInRows[j].add(val);
        valuesInCols[i].add(val);
        int indexI = (i / 3) * 3;
        int indexJ = (j / 3) * 3;
        int address = getHashCode(indexI, indexJ);
        valuesInBoxes.get(address).add(val);
    }
    static void undoUpDate(int i, int j, int val) {
        valuesInRows[j].remove(val);
        valuesInCols[i].remove(val);
        int indexI = (i / 3) * 3;
        int indexJ = (j / 3) * 3;
        int address = getHashCode(indexI, indexJ);
        valuesInBoxes.get(address).remove(val);
    }
    static boolean canChange(int i, int j) {
        if (boardForCheck[i][j] == 0) {
            return true;
        }
        return false;
    }

    private static int[][] getCopyOf(int[][] board) {
        int[][] newBoard = new int[board.length][board.length];
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard[0].length; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }
    private static void putValuesInMaps(){
        putDataForCols();
        putDataForRows();
        putDataForBoxes();
    }
    private static HashSet<Integer>[] valuesInCols = new HashSet[9]; // for checking values in columns
    private static HashSet<Integer>[] valuesInRows = new HashSet[9]; // for checking values in rows
    private static HashMap<Integer, HashSet<Integer>> valuesInBoxes = new HashMap<>();// for checking values in boxes(3X3)
    private static void putDataForCols() {
        for (int i = 0; i < 9; i++) {
            HashSet<Integer> set = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (boardForCheck[i][j] != 0) {
                    set.add(boardForCheck[i][j]);
                }
            }
            valuesInCols[i] = set;
        }
    }
    private static void putDataForRows() {
        for (int i = 0; i < 9; i++) {
            HashSet<Integer> set = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (boardForCheck[j][i] != 0) {
                    set.add(boardForCheck[j][i]);
                }
            }
            valuesInRows[i] = set;
        }
    }
    private static void putDataForBoxes() {
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                HashSet<Integer> set = new HashSet<>();
                for (int k = i; k < i + 3; k++) {
                    for (int l = j; l < j + 3; l++) {
                        if(boardForCheck[k][l] != 0) {
                            set.add(boardForCheck[k][l]);
                        }
                    }
                }
                int address = getHashCode(i, j);
                valuesInBoxes.put(address, set);
            }
        }
    }
    static int getHashCode(int i, int j){
        return (i * 31) + j;
    }
    private static boolean canPut(int i, int j, int val) {
        if (valuesInRows[j].contains(val)) {
            return false;
        }
        if (valuesInCols[i].contains(val)) {
            return false;
        }
        int indexI = (i / 3) * 3;
        int indexJ = (j / 3) * 3;
        int address = getHashCode(indexI, indexJ);
        if(valuesInBoxes.get(address).contains(val)){
            return false;
        }
        return true;
    }
}
