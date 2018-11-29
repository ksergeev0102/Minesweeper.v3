package lab2.model;

public class GamingField {
    private int flags;
    private int mines;
    private int[] value = {0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1};
    private int size;
    private Matrix matrixMines;
    private boolean[][] open_indicator;
    private boolean[][] flag_indicator;
    private Matrix forgame;
    private boolean[][] mines_indicator;

    public GamingField(int size, int mines) {
        this.mines = mines;
        this.size = size;
        this.flags = mines;
        this.matrixMines = new Matrix(size, size);
        this.flag_indicator = new boolean[size][size];
        this.open_indicator = new boolean[size][size];
        this.mines_indicator = new boolean[size][size];
        this.forgame = new Matrix(size, size);
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.matrixMines.setData(i, j, 0);
                this.open_indicator[i][j] = false;
                this.flag_indicator[i][j] = false;
                this.mines_indicator[i][j] = false;
            }
        }
    }

    public void inicializaiton(int mines, int m, int n) {
        this.matrixMines.randomFilling(mines, this.value);
        this.matrixMines.setData(m, n, 0);
        this.open_indicator[m][n] = true;
        this.forgame.FieldWithNumbers(this.matrixMines);
    }

    public int getValueForgame(int x, int y) {
        return this.forgame.getData(x, y);
    }

    public boolean checkMine(int x, int y) {
        if (this.matrixMines.getData(x, y) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkOpen(int x, int y) {
        if (this.open_indicator[x][y]) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkOpenFlag(int x, int y) {
        if (this.flag_indicator[x][y]) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkopenMine(int x, int y) {
        if (this.mines_indicator[x][y]) {
            return true;
        } else {
            return false;
        }
    }

    public int checkMines() {
        return this.mines;
    }

    public int getMine(int x, int y) {
        return this.matrixMines.getData(x, y);
    }

    public void openMines() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.matrixMines.getData(i, j) == 1) {
                    this.mines_indicator[i][j] = true;
                }
            }
        }
    }

    public int getFlags() {
        return this.flags;
    }

    public int getSize() {
        return this.size;
    }

    public boolean youWon() {
        int sum1 = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (!this.open_indicator[i][j]) {
                    sum1++;
                }
            }
        }
        if (sum1 == this.mines) {
            return true;
        } else {
            return false;
        }
    }

    public void openCell(int x, int y) {
        if (this.flag_indicator[x][y]) {
            return;
        } else if (forgame.getData(x, y) != 0 && this.matrixMines.getData(x, y) != 1) {
            this.open_indicator[x][y] = true;
        } else if (this.forgame.getData(x, y) == 0 && this.matrixMines.getData(x, y) != 1) {
            this.open_indicator[x][y] = true;
            if (y + 1 < this.size) {
                if (!this.open_indicator[x][y + 1]) {
                    openCell(x, y + 1);
                }
            }
            if (x - 1 >= 0) {
                if (!this.open_indicator[x - 1][y]) {
                    openCell(x - 1, y);
                }
            }
            if (x + 1 < this.size) {
                if (!this.open_indicator[x + 1][y]) {
                    openCell(x + 1, y);
                }
            }
            if (y - 1 >= 0) {
                if (!this.open_indicator[x][y - 1]) {
                    openCell(x, y - 1);
                }
            }
        } else if (this.forgame.getData(x, y) != 0 && this.matrixMines.getData(x, y) == 1) {
            this.open_indicator[x][y] = true;
        } else if (((x == 0 && y == 0) || (x == this.size && y == 0)
                || (x == 0 && y == this.size) || (x == this.size && y == this.size)) && this.matrixMines.getData(x, y) != 1) {
            this.open_indicator[x][y] = true;
        }
    }

    public void putFlag(int x, int y) {
        if (!this.flag_indicator[x][y] && this.flags > 0) {
            this.flag_indicator[x][y] = true;
            this.flags--;
        }
    }

    public void delFlag(int x, int y) {
        if (this.flag_indicator[x][y] && this.flags < this.mines) {
            this.flag_indicator[x][y] = false;
            this.flags++;
        }
    }

    public int sumFlags() {
        int sum = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.flag_indicator[i][j]) {
                    sum++;
                }
            }
        }
        return sum;
    }

    public void inverseFlag(int x, int y) {
        this.flag_indicator[x][y] = !this.flag_indicator[x][y];
    }
}
