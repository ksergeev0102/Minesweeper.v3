package lab2.model;


public class GamingField {
    private int flags;
    private int mines;
    int[] value = {0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1};
    private int size;
    private Matrix matrixMines;
    private boolean[][] open_indicator;
    private boolean[][] flag_indicator;
    private Matrix forgame;

    public GamingField(int size, int mines, int m, int n) {
        this.mines = mines;
        this.size = size;
        this.flags = mines;
        this.matrixMines = new Matrix(size, size);
        this.flag_indicator = new boolean[size][size];
        this.open_indicator = new boolean[size][size];
        this.forgame = new Matrix(size, size);
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.matrixMines.data[i][j] = 0;
                this.open_indicator[i][j] = false;
                this.flag_indicator[i][j] = false;
            }
        }
        this.matrixMines.randomFilling(mines, value);
        this.matrixMines.data[m][n] = 0;
        this.open_indicator[m][n] = true;
        this.forgame.FieldWithNumbers(this.matrixMines);
    }

    public boolean checkMine(int x, int y) {
        if (this.matrixMines.data[x][y] == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void showMines() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.open_indicator[i][j] == false && this.matrixMines.data[i][j] ==0) {
                    System.out.printf("[%d:%d]", i, j);
                } else if (this.open_indicator[i][j] == true && this.matrixMines.data[i][j] ==0 ) {
                    System.out.printf("( %d )", this.forgame.data[i][j]);
                } else if (this.flag_indicator[i][j] == false && this.matrixMines.data[i][j] ==1) {
                    System.out.printf("(bomb)");
                }
            }
            System.out.println("\n");
        }
    }

    public int checkMines() {
        return this.mines;
    }

    public int checkFlags() {
        return this.flags;
    }

    public int checkSize() {
        return this.size;
    }

    public void showField1() {
        this.matrixMines.showMatrix();
    }

    public void showField2() {
        this.forgame.showMatrix();
    }

    public int sumNoOpenElements() {
        int sum = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.open_indicator[i][j] == false) {
                    sum++;
                }
            }
        }
        return sum;
    }

    public void showFieldForPlayer() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.open_indicator[i][j] == false && this.flag_indicator[i][j] == false) {
                    System.out.printf("[%d:%d]", i, j);
                } else if (this.open_indicator[i][j] == true) {
                    System.out.printf("( %d )", this.forgame.data[i][j]);
                } else if (this.flag_indicator[i][j] == true) {
                    System.out.printf("( %d )", 9);
                }
            }
            System.out.println("\n");
        }
        System.out.println("Количество флажков:" + this.flags);
    }

    public void openCell(int x, int y) {
        if (this.flag_indicator[x][y] == true) {
            return;
        } else if (forgame.data[x][y] != 0 && this.matrixMines.data[x][y] != 1) {
            this.open_indicator[x][y] = true;
        } else if (this.forgame.data[x][y] == 0 && this.matrixMines.data[x][y] != 1) {
            this.open_indicator[x][y] = true;
            if (y + 1 < this.size) {
                if (this.open_indicator[x][y + 1] == false) {
                    openCell(x, y + 1);
                }
            }
            if (x - 1 >= 0) {
                if (this.open_indicator[x - 1][y] == false) {
                    openCell(x - 1, y);
                }
            }
            if (x + 1 < this.size) {
                if (this.open_indicator[x + 1][y] == false) {
                    openCell(x + 1, y);
                }
            }
            if (y - 1 >= 0) {
                if (this.open_indicator[x][y - 1] == false) {
                    openCell(x, y - 1);
                }
            }
        } else if (this.forgame.data[x][y] != 0 && this.matrixMines.data[x][y] == 1) {
            this.open_indicator[x][y] = true;
        } else if (((x == 0 && y == 0) || (x == this.size && y == 0)
                || (x == 0 && y == this.size) || (x == this.size && y == this.size)) && this.matrixMines.data[x][y] != 1) {
            this.open_indicator[x][y] = true;
        }
    }

    public void putFlag(int x, int y) {
        if (this.flag_indicator[x][y] != true && this.flags > 0) {
            this.flag_indicator[x][y] = true;
            this.flags--;
        } else {
            return;
        }
    }

    public void delFlag(int x, int y) {
        if (this.flag_indicator[x][y] != false && this.flags < this.mines) {
            this.flag_indicator[x][y] = false;
            this.flags++;
        } else {
            return;
        }
    }
}
