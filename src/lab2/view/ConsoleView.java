package lab2.view;

import lab2.model.Gamemodel;
import lab2.model.GamingField;

public class ConsoleView implements View {
    private GamingField gamingField;

    public ConsoleView(Gamemodel model) {
        this.gamingField = model.getField();
    }

    @Override
    public void showGameField() {
        for (int i = 0; i < this.gamingField.checkSize(); i++) {
            for (int j = 0; j < this.gamingField.checkSize(); j++) {
                if (this.gamingField.checkOpen(i, j) == false && this.gamingField.checkOpenFlag(i, j) == false) {
                    System.out.printf("[%d:%d]", i, j);
                } else if (this.gamingField.checkOpen(i, j) == true) {
                    System.out.printf("( %d )", this.gamingField.getValueForgame(i, j));
                } else if (this.gamingField.checkOpenFlag(i, j) == true) {
                    System.out.printf("( %d )", 9);
                }
            }
            System.out.println("\n");
        }
        System.out.println("Количество флажков:" + this.gamingField.getFlags());
    }

    @Override
    public void showMines() {
        for (int i = 0; i < this.gamingField.checkSize(); i++) {
            for (int j = 0; j < this.gamingField.checkSize(); j++) {
                if (this.gamingField.checkOpen(i, j) == false && this.gamingField.getMine(i,j)==0) {
                    System.out.printf("[%d:%d]", i, j);
                } else if (this.gamingField.checkOpen(i, j) == true && this.gamingField.getMine(i,j) ==0 ) {
                    System.out.printf("( %d )", this.gamingField.getValueForgame(i, j));
                } else if (this.gamingField.checkOpenFlag(i, j) == false && this.gamingField.getMine(i,j) ==1) {
                    System.out.printf("(bomb)");
                }
            }
            System.out.println("\n");
        }
    }

}
