package lab2.view;

import lab2.model.Gamemodel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

public class GraphicView extends JFrame {
    Scanner scanner = new Scanner(System.in);
    private Gamemodel model;
    private JPanel panel;
    private int sizeCanvas;
    private JLabel label;
    private int bombs; // количество бомб


    public GraphicView() {
        System.out.println("Введите размер поля: ");
        this.sizeCanvas = scanner.nextInt();
        System.out.println("Введите количество мин на поле: ");
        this.bombs = scanner.nextInt();
        this.model = new Gamemodel(this.sizeCanvas, this.bombs);
        initLabel();
        initPanel();
        initFrame();
    }

    private void initLabel() {
        label = new JLabel("Найди все бомбы!!!");
        add(label, BorderLayout.SOUTH);
    }

    public void setbox(int x, int y) {
        if (model.getField().checkOpen(x, y) == true) {
            JLabel box = new JLabel(Integer.toString(model.getField().getValueForgame(x, y)));
            box.setPreferredSize(new Dimension(30, 30));
            box.setLocation(30*x,30*y);
        } else {
            JLabel box = new JLabel("F");
            box.setPreferredSize(new Dimension(30, 30));
            box.setLocation(30*x,30*y);
        }
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int i = 0; i < sizeCanvas; i++) {
                    for (int j = 0; j < sizeCanvas; j++) {
                        if (model.getField().checkOpen(i, j) == false && model.getField().checkFlags(i, j) == false)
                            g.drawRect(i * 30, j * 30, 30, 30);
                        else if (model.getField().checkOpen(i, j) == true||model.getField().checkFlags(i,j) ==true) {
                            setbox(i, j);
                        }
                    }
                }
            }

        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / 30;
                int y = e.getY() / 30;
                if(model.getState() == false) {
                    model.setField(model.getField().checkMines(), x, y);
                }
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if(model.getField().checkMine(x,y)==true){
                        label.setText(getessage(x, y));
                        panel.repaint();
                        return;
                    }else {
                        model.getField().openCell(x, y);
                        label.setText(getessage(x, y));
                        panel.repaint();
                    }
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    model.getField().inverseFlag(x, y);
                    label.setText(getessage(x, y));
                    panel.repaint();
                }
            }
        });
        panel.setPreferredSize(new Dimension(30 * sizeCanvas, 30 * sizeCanvas));
        add(panel);
    }

    private String getessage(int x, int y) {
        if (model.getField().youWon()) {
            return "Вы выиграли";
        } else if (model.getField().checkMine(x, y) == true) {
            return "Взворвался";
        } else {
            return "Еще есть бомбы";
        }
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("MINESWEEPER");
        setResizable(false);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);

    }

}
