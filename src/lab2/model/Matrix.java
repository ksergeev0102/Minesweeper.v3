package lab2.model;

import java.util.Random;

public class Matrix {
    private int columns;
    private int rows;
    public int[][] data;

    public Matrix(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        this.data = new int[rows][columns];
    }

    //public int getData(int x, int y){
      //  return this.data[x][y];
    //}

    private int sumElements() {
        int sum = 0;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                sum += this.data[i][j];
            }
        }
        return sum;
    }

    private static int getRandom(int[] array) {
        int random = new Random().nextInt(array.length);
        return array[random];
    }

    public void randomFilling(int mines, int[] value) {
        while (this.sumElements() < mines) {
            for (int i = 0; i < this.rows; i++) {
                if (this.sumElements() < mines) {
                    for (int j = 0; j < this.columns; j++) {
                        if (this.sumElements() < mines) {
                            if (this.data[i][j] == 0) {
                                this.data[i][j] = getRandom(value);
                            }
                        }
                    }
                }
            }
        }
    }

    public void showMatrix() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                System.out.print(this.data[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public void FieldWithNumbers(Matrix matrix) {
        this.data[0][0]
                = matrix.data[0][1]
                + matrix.data[1][1]
                + matrix.data[1][0];

        this.data[this.rows - 1][this.columns - 1]
                = matrix.data[this.rows - 1][this.columns - 2]
                + matrix.data[this.rows - 2][this.columns - 2]
                + matrix.data[this.rows - 2][this.columns - 1];

        this.data[0][this.columns - 1]
                = matrix.data[0][this.columns - 2]
                + matrix.data[1][this.columns - 2] +
                matrix.data[1][this.columns - 1];

        this.data[this.rows - 1][0]
                = matrix.data[this.rows - 1][1]
                + matrix.data[this.rows - 2][1] +
                matrix.data[this.rows - 2][0];

        for (int i = 1; i < this.columns - 1; i++) {
            this.data[0][i] = matrix.data[0][i - 1]
                    + matrix.data[1][i - 1]
                    + matrix.data[1][i]
                    + matrix.data[1][i + 1]
                    + matrix.data[0][i + 1];
        }

        for (int i = 1; i < this.columns - 1; i++) {
            this.data[i][0]
                    = matrix.data[i - 1][0]
                    + matrix.data[i - 1][1]
                    + matrix.data[i][1]
                    + matrix.data[i + 1][1]
                    + matrix.data[i + 1][0];
        }

        for (int i = 1; i < this.columns - 1; i++) {
            this.data[this.rows - 1][i]
                    = matrix.data[this.rows - 1][i - 1]
                    + matrix.data[this.rows - 2][i - 1]
                    + matrix.data[this.rows - 2][i]
                    + matrix.data[this.rows - 2][i + 1]
                    + matrix.data[this.rows - 1][i + 1];
        }

        for (int i = 1; i < this.columns - 1; i++) {
            this.data[i][this.columns - 1]
                    = matrix.data[i - 1][this.columns - 1]
                    + matrix.data[i - 1][this.columns - 2]
                    + matrix.data[i][this.columns - 2]
                    + matrix.data[i + 1][this.columns - 2]
                    + matrix.data[i + 1][this.columns - 1];
        }

        for (int i = 1; i < this.rows - 1; i++) {
            for (int j = 1; j < this.columns - 1; j++) {
                this.data[i][j] =
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
