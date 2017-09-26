package de.struckmeierfliesen.ds.testing;

import java.util.Arrays;
import java.util.Random;

public class LShape {
    enum Direction {
        NE, NW, SE, SW;

        private static final Direction[] VALUES = values();
        private static final int SIZE = VALUES.length;
        private static final Random RANDOM = new Random();

        public static Direction getRandom()  {
            return VALUES[RANDOM.nextInt(SIZE)];
        }
    }

    int[][] coords = new int[3][2];
    Direction direction;

    // x and y are the coordinates of the corner of the L shape
    public LShape(int[] corner, Direction direction) {
        this.direction = direction;
        calculateCoordinates(corner);
    }

    public int[] getLocation() {
        return coords[0];
    }

    public int[][] getCoordinates() {
        return coords;
    }

    public void calculateCoordinates(int[] corner) {
        int x = corner[0];
        int y = corner[1];

        coords[0] = corner;
        switch (direction) {
            case NE:
                coords[1] = new int[]{x - 1, y};
                coords[2] = new int[]{x, y - 1};
                break;
            case NW:
                coords[1] = new int[]{x, y - 1};
                coords[2] = new int[]{x + 1, y};
                break;
            case SE:
                coords[1] = new int[]{x, y + 1};
                coords[2] = new int[]{x - 1, y};
                break;
            case SW:
                coords[1] = new int[]{x + 1, y};
                coords[2] = new int[]{x, y + 1};
                break;
        }
    }

    public boolean intersects(LShape tile) {
        int[][] coordinates = tile.getCoordinates();
        for (int i = 0; i < 3; i++) {
            if (Arrays.equals(coordinates[i], coords[i])) return true;
        }
        return false;
    }
}
