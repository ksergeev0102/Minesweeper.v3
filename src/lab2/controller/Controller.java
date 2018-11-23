package lab2.controller;

import lab2.exeptions.Goingabroad;
import lab2.model.Gamemodel;
import lab2.view.ConsoleView;

import javax.swing.*;
import java.util.Scanner;

public class Controller extends JFrame {
    private Gamemodel model;
    private ConsoleView view;
    Scanner scanner = new Scanner(System.in);


    public void FirstMove() throws Goingabroad {
        System.out.println("Введите размер поля: ");
        int size = scanner.nextInt();
        if (size > 20 || size == 1 || size == 0 || size < 0) {
            lab3.log.Log.printError("Неверный размер поля");
            throw new Goingabroad("Неверный размер поля");
        } else {
            System.out.println("Введите количество мин на поле: ");
            int mines = scanner.nextInt();
            if (mines > size * size || mines == 0 || mines < 0) {
                lab3.log.Log.printError("Неверное количество мин");
                throw new Goingabroad("Неверное количество мин");
            } else {
                System.out.println("Выберите координаты первого хода: ");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                if (x > size || y > size || x * y < 0) {
                    lab3.log.Log.printError("Выход за пределы поля!");
                    throw new Goingabroad("Выход за пределы поля!");
                } else {
                    model = new Gamemodel(size,mines);
                    view = new ConsoleView(model);
                    model.setField(mines,x,y);
                    model.getField().openCell(x, y);
                    view.showGameField();
                }
            }
        }
    }

    public void Move() throws Goingabroad {
        long start = System.nanoTime();
        while (!this.model.getField().youWon()) {
            System.out.println("Выберите дальнейшее действие : \n " +
                    "(1 - сделать ход/0 - поставить флажок/2 - удалить флажок)");
            int answ = scanner.nextInt();
            if (answ == 1) {
                System.out.println("Выберите координаты следующего хода: ");
                int x1 = scanner.nextInt();
                int y1 = scanner.nextInt();
                if (x1 > this.model.getField().checkSize() || y1 > this.model.getField().checkSize() || x1 * y1 < 0) {
                    lab3.log.Log.printError("Выход за пределы поля!");
                    throw new Goingabroad("Выход за пределы поля!");
                } else {
                    if (this.model.getField().checkMine(x1, y1) == true) {
                        System.out.println("Вы взорвались!");
                        view.showMines();
                        return;
                    } else {
                        this.model.getField().openCell(x1, y1);
                        view.showGameField();
                    }
                }
            } else if (answ == 0 && this.model.getField().getFlags() > 0) {
                System.out.println("Выберите координаты флажка: ");
                int x2 = scanner.nextInt();
                int y2 = scanner.nextInt();
                if (x2 > this.model.getField().checkSize() || y2 > this.model.getField().checkSize() || x2 * y2 < 0) {
                    lab3.log.Log.printError("Выход за пределы поля!");
                    throw new Goingabroad("Выход за пределы поля!");
                } else {
                    this.model.getField().putFlag(x2, y2);
                    view.showGameField();
                }
            } else if (answ == 2 && this.model.getField().getFlags() < this.model.getField().checkMines()) {
                System.out.println("Выберите координаты флажка: ");
                int x3 = scanner.nextInt();
                int y3 = scanner.nextInt();
                if (x3 > this.model.getField().checkSize() || y3 > this.model.getField().checkSize() || x3 * y3 < 0) {
                    lab3.log.Log.printError("Выход за пределы поля!");
                    throw new Goingabroad("Выход за пределы поля!");
                } else {
                    this.model.getField().delFlag(x3, y3);
                    view.showGameField();
                }
            }
        }
        System.out.println("Вы выиграли!");
        long finish2 = System.nanoTime();
        System.out.println("Время :" + String.format("%,12d",(finish2-start)/1000000000) + "sec");
    }
}
