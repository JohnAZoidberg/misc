package de.struckmeierfliesen.ds.testing;


import java.util.Arrays;
import java.util.Random;

public class ProceduraL {
    private final Random rand = new Random();
    private static final char ILLEGAL_CHAR = '-';
    private static final char BLANK_CHAR = '0';

    private int size;

    public ProceduraL(int exponent) {
        // set up variables
        size = (int) Math.pow(2, exponent);
        int maxTiles = (size * size - 1) / 3;

        // set up board
        char[][] board = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = BLANK_CHAR;
            }
        }
        int[] illegalSquare = new int[]{rand.nextInt(size), rand.nextInt(size)};
        board[illegalSquare[0]][illegalSquare[1]] = ILLEGAL_CHAR;

        printBoard(board);
        // solve
        char[][] solvedBoard = placeNewTile(copyBoard(board));
        System.out.print("Success!");
        printBoard(solvedBoard);
    }

    private char[][] copyBoard(char[][] board) {
        char[][] newBoard = new char[board.length][board.length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, board.length);
        }
        return newBoard;
    }

    private char[][] placeNewTile(char[][] board) {
        if (isSolved(board)) {
            return board;
        } else {
            int[][] tile;
            for (int hash = 0; hash < size * size * 4; hash++) {
                tile = getNextTile(hash);
                if (isValidTile(tile, board)) {
                    // actually place it on the board
                    char symbol = (char) (getHighestSymbol(board) + 1);
                    for (int[] tilePiece : tile) {
                        board[tilePiece[0]][tilePiece[1]] = symbol;
                    }
                    printBoard(board);
                    if (noMovesLeft(board)) {
                        // just undo and return
                        for (int[] tilePiece : tile) {
                            board[tilePiece[0]][tilePiece[1]] = BLANK_CHAR;
                        }
                        return board;
                    }
                }
            }
            char[][] newBoard = placeNewTile(board);
            if (Arrays.deepEquals(newBoard, board)) {
                newBoard = removeLastTile(newBoard);
            }
            return newBoard;
        }
    }

    private int[][] getNextTile(int hash) {
        int rotation = hash % 4;
        int pos = (hash - rotation) / 4;
        int y = pos % size;
        int x = (pos - y) / size;

        int[][] tile = new int[3][2];
        switch (rotation) {
            case 0:
                tile[1] = new int[]{x - 1, y};
                tile[2] = new int[]{x, y - 1};
                break;
            case 1:
                tile[1] = new int[]{x, y - 1};
                tile[2] = new int[]{x + 1, y};
                break;
            case 2:
                tile[1] = new int[]{x, y + 1};
                tile[2] = new int[]{x - 1, y};
                break;
            case 3:
                tile[1] = new int[]{x + 1, y};
                tile[2] = new int[]{x, y + 1};
                break;
        }
        return tile;
    }

    private char getHighestSymbol(char[][] board) {
        char symbol = 'A' - 1;
        for (char[] row : board) {
            for (char column : row) {
                if (column > symbol) symbol = column;
            }
        }
        return symbol;
    }

    private boolean noMovesLeft(char[][] board) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                try {
                    if (board[i][j] == BLANK_CHAR && board[i][j + 1] == BLANK_CHAR &&
                            (board[i + 1][j] == BLANK_CHAR || board[i + 1][j + 1] == BLANK_CHAR
                            || board[i - 1][j] == BLANK_CHAR || board[i - 1][j + 1] == BLANK_CHAR)) {
                        return false;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
        return true;
    }

    private char[][] removeLastTile(char[][] board) {
        char symbol = getHighestSymbol(board);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == symbol) board[i][j] = BLANK_CHAR;
            }
        }
        return board;
    }

    private boolean isValidTile(int[][] tile, char[][] board) {
        for (int[] tileSquare : tile) {
            // check if inside the board
            for (int coord : tileSquare) {
                if (coord < 0 || coord >= size) {
                    return false;
                }
            }

            /*// check if on illegal square
            if (Arrays.equals(tileSquare, illegalSquare)) {
                return false;
            }*/

            // check if on top of other tile or on the illegal square
            if (board[tileSquare[0]][tileSquare[1]] != BLANK_CHAR) {
                return false;
            }
        }
        return true;
    }

    private boolean isSolved(char[][] board) {
        for (char[] row : board) {
            for (char column : row) {
                if (column == BLANK_CHAR) return false;
            }
        }
        return true;
    }

    private int[][] getRandomTile() {
        int[][] tile = new int[3][2];
        int x = rand.nextInt(size);
        int y = rand.nextInt(size);
        tile[0] = new int[]{x, y}; // corner
        tile[1] = new int[]{x, y + randPosNeg()};
        tile[2] = new int[]{x + randPosNeg(), y};
        return tile;
    }

    private void printBoard(char[][] board) {
        System.out.println();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j]);
                if (j + 1 < size) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    private int randPosNeg() {
        return rand.nextBoolean() ? 1 : -1;
    }
}
