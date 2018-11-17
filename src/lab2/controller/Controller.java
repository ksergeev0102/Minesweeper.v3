package lab2.controller;

import lab2.exeptions.Goingabroad;
import lab2.model.GamingField;
import lab2.model.Model;
import lab2.view.ConsoleView;
import lab2.view.View;

import java.util.Scanner;

public class Controller {
    Model model = new Model();
    View view = new ConsoleView();
    Scanner scanner = new Scanner(System.in);


    public GamingField FirstMove() throws Goingabroad {
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
                    GamingField gamingField = model.getField(size, mines, x, y);
                    gamingField.openCell(x, y);
                    view.showGameField(gamingField);
                    return gamingField;
                }
            }
        }
    }

    public void Move() throws Goingabroad {
        GamingField gamingField = FirstMove();
        long start = System.nanoTime();
        while (gamingField.sumNoOpenElements() > gamingField.checkMines()) {
            System.out.println("Выберите дальнейшее действие : \n " +
                    "(1 - сделать ход/0 - поставить флажок/2 - удалить флажок)");
            int answ = scanner.nextInt();
            if (answ == 1) {
                System.out.println("Выберите координаты следующего хода: ");
                int x1 = scanner.nextInt();
                int y1 = scanner.nextInt();
                if (x1 > gamingField.checkSize() || y1 > gamingField.checkSize() || x1 * y1 < 0) {
                    lab3.log.Log.printError("Выход за пределы поля!");
                    throw new Goingabroad("Выход за пределы поля!");
                } else {
                    if (gamingField.checkMine(x1, y1) == true) {
                        System.out.println("Вы взорвались!");
                        gamingField.showMines();
                        return;
                    } else {
                        gamingField.openCell(x1, y1);
                        view.showGameField(gamingField);
                    }
                }
            } else if (answ == 0 && gamingField.checkFlags() > 0) {
                System.out.println("Выберите координаты флажка: ");
                int x2 = scanner.nextInt();
                int y2 = scanner.nextInt();
                if (x2 > gamingField.checkSize() || y2 > gamingField.checkSize() || x2 * y2 < 0) {
                    lab3.log.Log.printError("Выход за пределы поля!");
                    throw new Goingabroad("Выход за пределы поля!");
                } else {
                    gamingField.putFlag(x2, y2);
                    view.showGameField(gamingField);
                }
            } else if (answ == 2 && gamingField.checkFlags() < gamingField.checkMines()) {
                System.out.println("Выберите координаты флажка: ");
                int x3 = scanner.nextInt();
                int y3 = scanner.nextInt();
                if (x3 > gamingField.checkSize() || y3 > gamingField.checkSize() || x3 * y3 < 0) {
                    lab3.log.Log.printError("Выход за пределы поля!");
                    throw new Goingabroad("Выход за пределы поля!");
                } else {
                    gamingField.delFlag(x3, y3);
                    view.showGameField(gamingField);
                }
            }
        }
        System.out.println("Вы выиграли!");
        long finish2 = System.nanoTime();
        System.out.println("Время :" + String.format("%,12d",(finish2-start)/1000000000) + "sec");
    }
}
