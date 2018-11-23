package lab2.model;


import java.awt.*;

public class GamingField {
    private int flags;
    private int mines;
    int[] value = {0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1};
    private int size;
    private Matrix matrixMines;
    private boolean[][] open_indicator;
    private boolean[][] flag_indicator;
    private Matrix forgame;

    public GamingField(int size, int mines) {
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
    }

    public void inicialized(int mines, int m, int n) {
        this.matrixMines.randomFilling(mines, value);
        this.matrixMines.data[m][n] = 0;
        this.open_indicator[m][n] = true;
        this.forgame.FieldWithNumbers(this.matrixMines);
    }

    public int getValueForgame(int x, int y) {
        return this.forgame.data[x][y];
    }

    public boolean checkMine(int x, int y) {
        if (this.matrixMines.data[x][y] == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkOpen(int x, int y) {
        if (this.open_indicator[x][y] == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkOpenFlag(int x, int y) {
        if (this.flag_indicator[x][y] == true) {
            return true;
        } else {
            return false;
        }
    }

    public int checkMines() {
        return this.mines;
    }

    public int getMine(int x, int y) {
        return this.matrixMines.data[x][y];
    }

    public boolean checkFlags(int x, int y) {
        if (this.flag_indicator[x][y] == true) {
            return true;
        } else {
            return false;
        }
    }

    public int getFlags() {
        return this.flags;
    }

    public int checkSize() {
        return this.size;
    }

    public boolean youWon() {
        int sum = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.open_indicator[i][j] == false) {
                    sum++;
                }
            }
        }
        if (sum == this.mines) {
            return true;
        } else {
            return false;
        }
    }

    public int sumNoOpen() {
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

    public void inverseFlag(int x, int y) {
        this.flag_indicator[x][y] = !this.flag_indicator[x][y];
    }

    void paintBomb(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x * 9 + 7, y * 9 + 10, 18, 10);
        g.fillRect(x * 9 + 11, y * 9 + 6, 10, 18);
        g.fillRect(x * 9 + 9, y * 9 + 8, 14, 14);
        g.setColor(Color.white);
        g.fillRect(x * 9 + 11, y * 9 + 10, 4, 4);
    }

    void paintString(Graphics g, String str, int x, int y, Color color) {
        g.setColor(color);
        g.setFont(new Font("", Font.BOLD, 9));
        g.drawString(str, x * 9 + 8, y * 9 + 26);
    }

    public void paint(Graphics g, int x, int y) {
        g.setColor(Color.lightGray);
        g.drawRect(x * 9, y * 9, 9, 9);
        if (!this.checkOpen(x, y)) {
            if ((this.checkMine(x, y) || this.youWon()) && this.checkMine(x, y)) paintBomb(g, x, y, Color.black);
            else {
                g.setColor(Color.lightGray);
                g.fill3DRect(x * 9, y * 9, 9, 9, true);
                if (this.checkOpenFlag(x, y)) paintString(g, "F", x, y, Color.red);
            }
        } else if (this.checkMine(x, y)) paintBomb(g, x, y, this.checkMine(x, y) ? Color.red : Color.black);
        else if (this.getValueForgame(x, y) > 0)
            paintString(g, Integer.toString(this.getValueForgame(x, y)), x, y, new Color(0x0000FF));
    }
}
