package lab2.view;

import lab2.model.Gamemodel;
import lab2.model.GamingField;

public class ConsoleView implements View {
    private GamingField gamingField;

    public ConsoleView(Gamemodel model) {
        gamingField = model.getField();
    }

    @Override
    public void showGameField() {
        for (int i = 0; i < gamingField.getSize(); i++) {
            for (int j = 0; j < gamingField.getSize(); j++) {
                if (!gamingField.checkOpen(i, j)&& !gamingField.checkOpenFlag(i, j)) {
                    System.out.printf("[%d:%d]", i, j);
                } else if (gamingField.checkOpen(i, j)) {
                    System.out.printf("( %d )", gamingField.getValueForgame(i, j));
                } else if (gamingField.checkOpenFlag(i, j)) {
                    System.out.printf("( %d )", 9);
                }
            }
            System.out.println("\n");
        }
        System.out.println("Количество флажков:" + gamingField.getFlags());
    }

    @Override
    public void showMines() {
        for (int i = 0; i < gamingField.getSize(); i++) {
            for (int j = 0; j < gamingField.getSize(); j++) {
                if (!gamingField.checkOpen(i, j)&& gamingField.getMine(i,j)==0) {
                    System.out.printf("[%d:%d]", i, j);
                } else if (gamingField.checkOpen(i, j)&& gamingField.getMine(i,j) ==0 ) {
                    System.out.printf("( %d )", gamingField.getValueForgame(i, j));
                } else if (gamingField.checkOpenFlag(i, j) && gamingField.getMine(i,j) ==1) {
                    System.out.printf("(bomb)");
                }
            }
            System.out.println("\n");
        }
    }

}
