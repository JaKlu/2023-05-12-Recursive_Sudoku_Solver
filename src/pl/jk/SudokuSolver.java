package pl.jk;

public class SudokuSolver {
    public static final int GRID_SIZE = 9;

    public static void main(String[] args) {
        int[][] board = {
                {0, 0, 8, 0, 7, 0, 0, 1, 0},
                {0, 0, 7, 3, 0, 2, 0, 8, 0},
                {0, 0, 0, 8, 9, 1, 0, 0, 0},
                {2, 0, 0, 0, 6, 0, 0, 0, 5},
                {0, 0, 4, 9, 0, 3, 1, 0, 0},
                {6, 0, 0, 0, 8, 0, 0, 0, 4},
                {0, 0, 0, 7, 3, 9, 0, 0, 0},
                {0, 3, 0, 5, 0, 6, 9, 0, 0},
                {0, 4, 0, 0, 1, 0, 5, 0, 0}
        };
        System.out.println("Let's play Sudoku!");
        printBoard(board);

        if (solveSudoku(board)) {
            System.out.println("Sudoku solved!");
            printBoard(board);
        } else {
            System.out.println("Sudoku cannot be solved.");
        }
    }

    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInColumn(int[][] board, int number, int column) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInSquare(int[][] board, int number, int row, int column) {
        int squareRowIndex = row - row % 3;
        int squareColumnIndex = column - column % 3;
        for (int i = squareRowIndex; i < squareRowIndex + 3; i++) {
            for (int j = squareColumnIndex; j < squareColumnIndex + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
        return (!isNumberInRow(board, number, row) &&
                !isNumberInColumn(board, number, column) &&
                !isNumberInSquare(board, number, row, column));
    }

    private static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                if (board[row][column] == 0) {
                    for (int numberToFill = 1; numberToFill <= GRID_SIZE; numberToFill++) {
                        if (isValidPlacement(board, numberToFill, row, column)) {
                            board[row][column] = numberToFill;

                            if (solveSudoku(board)) {
                                return true;
                            } else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-".repeat(29));
            }
            for (int column = 0; column < GRID_SIZE; column++) {
                if (column % 3 == 0 && column != 0) {
                    System.out.print("|");
                }
                System.out.print(" " + (board[row][column] == 0 ? "□" : board[row][column]) + " ");
            }
            System.out.println();
        }
    }
}
