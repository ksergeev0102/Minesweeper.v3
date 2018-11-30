package lab2.model;

public class GamingField {
    private int flags;
    private int mines;
    private int[] value = {0, 1, 0, 0, 0, 0, 0, 0, 0, 0};
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
        matrixMines.randomFilling(mines, value);
        matrixMines.setData(m, n, 0);
        open_indicator[m][n] = true;
        forgame.FieldWithNumbers(matrixMines);
    }

    public int getValueForgame(int x, int y) {
        return forgame.getData(x, y);
    }

    public boolean checkMine(int x, int y) {
        if (matrixMines.getData(x, y) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkOpen(int x, int y) {
        if (open_indicator[x][y]) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkOpenFlag(int x, int y) {
        if (flag_indicator[x][y]) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkopenMine(int x, int y) {
        if (mines_indicator[x][y]) {
            return true;
        } else {
            return false;
        }
    }

    public int checkMines() {
        return mines;
    }

    public int getMine(int x, int y) {
        return matrixMines.getData(x, y);
    }

    public void openMines() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrixMines.getData(i, j) == 1) {
                    mines_indicator[i][j] = true;
                }
            }
        }
    }

    public int getFlags() {
        return flags;
    }

    public int getSize() {
        return size;
    }

    public boolean youWon() {
        int sum1 = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!open_indicator[i][j]) {
                    sum1++;
                }
            }
        }
        if (sum1 == mines) {
            return true;
        } else {
            return false;
        }
    }

    public void openCell(int x, int y) {
        if (flag_indicator[x][y]) {
            return;
        } else if (forgame.getData(x, y) != 0 && matrixMines.getData(x, y) != 1) {
            open_indicator[x][y] = true;
        } else if (forgame.getData(x, y) == 0 && matrixMines.getData(x, y) != 1) {
            open_indicator[x][y] = true;
            if (y + 1 < size) {
                if (!open_indicator[x][y + 1]) {
                    openCell(x, y + 1);
                }
            }
            if (x - 1 >= 0) {
                if (!open_indicator[x - 1][y]) {
                    openCell(x - 1, y);
                }
            }
            if (x + 1 < size) {
                if (!open_indicator[x + 1][y]) {
                    openCell(x + 1, y);
                }
            }
            if (y - 1 >= 0) {
                if (!open_indicator[x][y - 1]) {
                    openCell(x, y - 1);
                }
            }
        } else if (forgame.getData(x, y) != 0 && matrixMines.getData(x, y) == 1) {
            open_indicator[x][y] = true;
        } else if (((x == 0 && y == 0) || (x == size && y == 0)
                || (x == 0 && y == size) || (x == size && y == size)) && matrixMines.getData(x, y) != 1) {
            open_indicator[x][y] = true;
        }
    }

    public void putFlag(int x, int y) {
        if (!flag_indicator[x][y] && flags > 0) {
            flag_indicator[x][y] = true;
            flags--;
        }
    }

    public void delFlag(int x, int y) {
        if (flag_indicator[x][y] && flags < mines) {
            flag_indicator[x][y] = false;
            flags++;
        }
    }

    public int sumFlags() {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (flag_indicator[i][j]) {
                    sum++;
                }
            }
        }
        return sum;
    }

    public void inverseFlag(int x, int y) {
        flag_indicator[x][y] = !flag_indicator[x][y];
    }
}