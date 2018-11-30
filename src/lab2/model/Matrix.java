package lab2.model;

import java.util.Random;

public class Matrix {
    private int columns;
    private int rows;
    private int[][] data;

    public int getData(int x, int y) {
        return data[x][y];
    }

    public void setData(int x, int y, int value) {
        data[x][y] = value;
    }

    public Matrix(int c, int r) {
        columns = c;
        rows = r;
        data = new int[rows][columns];
    }

    private int sumElements() {
        int sum = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sum += data[i][j];
            }
        }
        return sum;
    }

    private static int getRandom(int[] array) {
        int random = new Random().nextInt(array.length);
        return array[random];
    }

    public void randomFilling(int mines, int[] value) {
        while (sumElements() < mines) {
            for (int i = 0; i < rows; i++) {
                if (sumElements() < mines) {
                    for (int j = 0; j < columns; j++) {
                        if (sumElements() < mines) {
                            if (data[i][j] == 0) {
                                data[i][j] = getRandom(value);
                            }
                        }
                    }
                }
            }
        }
    }

    public void FieldWithNumbers(Matrix matrix) {
        data[0][0]
                = matrix.data[0][1]
                + matrix.data[1][1]
                + matrix.data[1][0];

        data[rows - 1][columns - 1]
                = matrix.data[rows - 1][columns - 2]
                + matrix.data[rows - 2][columns - 2]
                + matrix.data[rows - 2][columns - 1];

        data[0][columns - 1]
                = matrix.data[0][columns - 2]
                + matrix.data[1][columns - 2] +
                matrix.data[1][columns - 1];

        data[rows - 1][0]
                = matrix.data[rows - 1][1]
                + matrix.data[rows - 2][1] +
                matrix.data[rows - 2][0];

        for (int i = 1; i < columns - 1; i++) {
            data[0][i] = matrix.data[0][i - 1]
                    + matrix.data[1][i - 1]
                    + matrix.data[1][i]
                    + matrix.data[1][i + 1]
                    + matrix.data[0][i + 1];
        }

        for (int i = 1; i < columns - 1; i++) {
            data[i][0]
                    = matrix.data[i - 1][0]
                    + matrix.data[i - 1][1]
                    + matrix.data[i][1]
                    + matrix.data[i + 1][1]
                    + matrix.data[i + 1][0];
        }

        for (int i = 1; i < columns - 1; i++) {
            data[rows - 1][i]
                    = matrix.data[rows - 1][i - 1]
                    + matrix.data[rows - 2][i - 1]
                    + matrix.data[rows - 2][i]
                    + matrix.data[rows - 2][i + 1]
                    + matrix.data[rows - 1][i + 1];
        }

        for (int i = 1; i < columns - 1; i++) {
            data[i][columns - 1]
                    = matrix.data[i - 1][columns - 1]
                    + matrix.data[i - 1][columns - 2]
                    + matrix.data[i][columns - 2]
                    + matrix.data[i + 1][columns - 2]
                    + matrix.data[i + 1][columns - 1];
        }

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < columns - 1; j++) {
                data[i][j] =
                        matrix.data[i - 1][j - 1] +
                                matrix.data[i - 1][j] +
                                matrix.data[i - 1][j + 1] +
                                matrix.data[i][j - 1] +
                                matrix.data[i][j + 1] +
                                matrix.data[i + 1][j - 1] +
                                matrix.data[i + 1][j] +
                                matrix.data[i + 1][j + 1];
            }
        }
    }
}
