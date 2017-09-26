package de.struckmeierfliesen.ds.testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class LGame {
    Random random = new Random();

    int size;
    int area;
    int maxTiles;
    int[] illegalSquare;
    ArrayList<LShape> tiles;

    public LGame(int size) {
        if (!isPowerOfTwo(size)) {
            throw new IllegalArgumentException("Size must be a power of two!");
        }
        this.size = size;
        area = size * size;
        Random random = new Random();
        illegalSquare = new int[] {random.nextInt(size), random.nextInt(size)};
        maxTiles = (area - 1) / 3;
        tiles = new ArrayList<>(maxTiles);
        placeTile();
    }

    private void placeTile() {
        LShape tile = new LShape(
                new int[] {random.nextInt(size),
                        random.nextInt(size)},
                LShape.Direction.getRandom()
        );
        if (validTile(tile)) {
            tiles.add(tile);
            printBoard();
        }
        if (tiles.size() < maxTiles) {
            placeTile();
        }
    }

    private void printBoard() {
        char symbol = 'A';
        int[][] board = new int[size][size];
        board[illegalSquare[1]][illegalSquare[0]] = '-';
        for (LShape tile : tiles) {
            int[][] coordinates = tile.getCoordinates();
            for (int[] point : coordinates) {
                board[point[1]][point[0]] = symbol;
            }
            symbol++;
        }

        for (int[] row : board) {
            for (int i = 0; i < size; i++) {
                int column = row[i];
                if (column == 0) {
                    System.out.print('0');
                } else {
                    System.out.print((char) column);
                }
                if (i + 1 < size) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean validTile(LShape tile) {
        int[][] coordinates = tile.getCoordinates();
        for (int[] is : coordinates) {
            if (Arrays.equals(is, illegalSquare)) {
                return false;//throw new IndexOutOfBoundsException("The tile lies on the illegal square.");
            }
            for (int i : is) {
                if (i < 0 || i >= size) {
                    return false;//throw new IndexOutOfBoundsException("The tile does not lie inside the board.");
                }
            }
            for (LShape t : tiles) {
                if (t.intersects(tile)) return false;
            }
        }
        return true;
    }

    public boolean isPowerOfTwo(int n) {
        if (n < 2) {
            return false;
        } else if (n == 2) {
            return true;
        } else if (n % 2 == 0) {
            return isPowerOfTwo(n / 2);
        } else {
            return false;
        }
    }
}
