package lab2.view;

import lab2.exeptions.Goingabroad;

public interface View {
    void showGameField() throws Goingabroad;
    void showMines();
}
