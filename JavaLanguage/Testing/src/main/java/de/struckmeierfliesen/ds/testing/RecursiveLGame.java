package de.struckmeierfliesen.ds.testing;

import java.util.Arrays;
import java.util.Random;

public class RecursiveLGame {
    enum Direction {
        NW, NE, SW, SE
    }

    private static final int ILLEGAL = -1;
    private static final int BLANK = 0;
    private int symbol = 1;

    public RecursiveLGame(int power) {
        int size = (int) Math.pow(2, power);
        // set up board
        int[][] board = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[j][i] = BLANK;//(int) (65 + i * size + j);
            }
        }
        Random rand = new Random();
        int[] illegalSquare = new int[]{rand.nextInt(size), rand.nextInt(size)};
        board[illegalSquare[0]][illegalSquare[1]] = ILLEGAL;

        printBoard(board);
        int[][] northWest = getPartialBoard(board, Direction.NW);
        printBoard(northWest);
        int[][] northEast = getPartialBoard(board, Direction.NE);
        printBoard(northEast);
        int[][] southWest = getPartialBoard(board, Direction.SW);
        printBoard(southWest);
        int[][] southEast = getPartialBoard(board, Direction.SE);
        printBoard(southEast);
        printBoard(mergeBoards(northWest, northEast, southWest, southEast));

        printBoard(fillBoard(board, illegalSquare, -2));


    }

    private int[][] mergeBoards(int[][] northWest, int[][] northEast, int[][] southWest, int[][] southEast) {
        int halfSize = northWest.length;
        int[][] mergedBoard = new int[halfSize * 2][halfSize * 2];
        deepCopy(northWest, mergedBoard, 0, 0, 0,           0,          halfSize);
        deepCopy(northEast, mergedBoard, 0, 0, halfSize,    0,          halfSize);
        deepCopy(southWest, mergedBoard, 0, 0, 0,           halfSize,   halfSize);
        deepCopy(southEast, mergedBoard, 0, 0, halfSize,    halfSize,   halfSize);
        return mergedBoard;
    }

    public int[][] fillBoard(int[][] board, int[] illegalSquare, int illegalLChar) {
        int size = board.length;
        if (size == 2) {
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    if (!Arrays.equals(illegalSquare, new int[]{x, y})) {
                        board[x][y] = symbol;
                    }
                }
            }
            symbol++;
            printBoard(board);
            return board;
        } else {
            int halfSize = size / 2;

            int[] northWestIllegal = {halfSize - 1, halfSize - 1};
            int[] northEastIllegal = {halfSize, halfSize - 1};
            int[] southWestIllegal = {halfSize - 1, halfSize};
            int[] southEastIllegal = {halfSize, halfSize};

            int[][] northWest;
            int[][] northEast;
            int[][] southWest;
            int[][] southEast;

            illegalLChar--;
            Direction illegalLocation;
            if (illegalSquare[0] < halfSize) { // WEST
                if (illegalSquare[1] < halfSize) { // NORTH
                    illegalLocation = Direction.NW;
                    northWest = fillBoard(getPartialBoard(board, Direction.NW),
                            illegalSquare, illegalLChar);

                    board[southWestIllegal[0]][southWestIllegal[1]] = illegalLChar;
                    southWest = fillBoard(getPartialBoard(board, Direction.SW),
                            new int[]{southWestIllegal[0], southWestIllegal[1] - halfSize}, illegalLChar);
                } else { // SOUTH
                    illegalLocation = Direction.SW;
                    southWest = fillBoard(getPartialBoard(board, Direction.SW),
                            new int[]{illegalSquare[0], illegalSquare[1] - halfSize}, illegalLChar);

                    board[northWestIllegal[0]][northWestIllegal[1]] = illegalLChar;
                    northWest = fillBoard(getPartialBoard(board, Direction.NW),
                            northWestIllegal, illegalLChar);
                }

                board[northEastIllegal[0]][northEastIllegal[1]] = illegalLChar;
                northEast = fillBoard(getPartialBoard(board, Direction.NE),
                        new int[]{northEastIllegal[0] - halfSize, northEastIllegal[1]}, illegalLChar);

                board[southEastIllegal[0]][southEastIllegal[1]] = illegalLChar;
                southEast = fillBoard(getPartialBoard(board, Direction.SE),
                        new int[]{southEastIllegal[0] - halfSize, southEastIllegal[1] - halfSize}, illegalLChar);
            } else { // EAST
                if (illegalSquare[1] < halfSize) { // NORTH
                    illegalLocation = Direction.NE;
                    northEast = fillBoard(getPartialBoard(board, Direction.NE),
                            new int[]{illegalSquare[0] - halfSize, illegalSquare[1]}, illegalLChar);

                    board[southEastIllegal[0]][southEastIllegal[1]] = illegalLChar;
                    southEast = fillBoard(getPartialBoard(board, Direction.SE),
                            new int[]{southEastIllegal[0] - halfSize, southEastIllegal[1] - halfSize}, illegalLChar);
                } else { // SOUTH
                    illegalLocation = Direction.SE;
                    southEast = fillBoard(getPartialBoard(board, Direction.SE),
                            new int[]{illegalSquare[0] - halfSize, illegalSquare[1] - halfSize}, illegalLChar);

                    board[northEastIllegal[0]][northEastIllegal[1]] = illegalLChar;
                    northEast = fillBoard(getPartialBoard(board, Direction.NE),
                            new int[]{northEastIllegal[0] - halfSize, northEastIllegal[1]}, illegalLChar);
                }

                board[northWestIllegal[0]][northWestIllegal[1]] = illegalLChar;
                northWest = fillBoard(getPartialBoard(board, Direction.NW),
                        northWestIllegal, illegalLChar);

                board[southWestIllegal[0]][southWestIllegal[1]] = illegalLChar;
                southWest = fillBoard(getPartialBoard(board, Direction.SW),
                        new int[]{southWestIllegal[0], southWestIllegal[1] - halfSize}, illegalLChar);
            }
            int[][] newBoard = mergeBoards(northWest, northEast, southWest, southEast);
            printBoard(newBoard);
            return newBoard;
        }
    }

    private int[][] getPartialBoard(int[][] board, Direction direction) {
        int halfSize = board.length / 2;
        int[][] newBoard = new int[halfSize][halfSize];
        switch (direction) {
            case NW:
                deepCopy(board, newBoard, 0, 0, 0, 0, halfSize);
                break;
            case NE:
                deepCopy(board, newBoard, halfSize, 0, 0, 0, halfSize);
                break;
            case SW:
                deepCopy(board, newBoard, 0, halfSize, 0, 0, halfSize);
                break;
            case SE:
                deepCopy(board, newBoard, halfSize, halfSize, 0, 0, halfSize);
                break;
        }
        Object b = (String) null;
        return newBoard;
    }

    private int[][] deepCopy(int[][] source, int[][] destination, int srcX, int srcY, int destX, int destY, int length) {
        for (int i = 0; i < length; i++) {
            System.arraycopy(source[i + srcX], srcY, destination[i + destX], destY, length);
        }
        return destination;
    }

    private void printBoard(int[][] board) {
        int size = board.length;
        System.out.println();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int symbol = board[x][y];
                if (symbol < 10 && symbol >= 0) {
                    System.out.print(" ");
                }
                System.out.print(symbol);
                if (x + 1 < size) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
