package lab2.view;

import lab2.model.Gamemodel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

public class GraphicView extends JFrame {
    private TimerLabel timeLabel;
    Scanner scanner = new Scanner(System.in);
    private Gamemodel model;
    private JPanel panel;
    private int sizeCanvas;
    private JLabel label;
    private int bombs;

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
        label = new JLabel("Откройте все пустые ячейки");
        add(label, BorderLayout.SOUTH);
    }

    public void setIm(Graphics g, int x, int y) {
        ImageIcon imageIcon;
        if (model.getField().checkOpen(x, y) == true) {
            if (model.getField().getValueForgame(x, y) == 0 && model.getField().checkMine(x,y) == false&&
                    model.getField().checkOpenFlag(x, y) == false) {
                imageIcon = new ImageIcon("./src/Images/0.jpg");
                g.drawImage(imageIcon.getImage(), x * 30, y * 30, 30, 30, this);
            } else if (model.getField().getValueForgame(x, y) == 1 && model.getField().checkMine(x,y) == false&&
                    model.getField().checkOpenFlag(x, y) == false) {
                imageIcon = new ImageIcon("./src/Images/1.jpg");
                g.drawImage(imageIcon.getImage(), x * 30, y * 30, 30, 30, this);
            } else if (model.getField().getValueForgame(x, y) == 2 && model.getField().checkMine(x,y) == false&&
                    model.getField().checkOpenFlag(x, y) == false) {
                imageIcon = new ImageIcon("./src/Images/2.jpg");
                g.drawImage(imageIcon.getImage(), x * 30, y * 30, 30, 30, this);
            } else if (model.getField().getValueForgame(x, y) == 3 && model.getField().checkMine(x,y) == false&&
                    model.getField().checkOpenFlag(x, y) == false) {
                imageIcon = new ImageIcon("./src/Images/3.jpg");
                g.drawImage(imageIcon.getImage(), x * 30, y * 30, 30, 30, this);
            } else if (model.getField().getValueForgame(x, y) == 4 && model.getField().checkMine(x,y) == false&&
                    model.getField().checkOpenFlag(x, y) == false) {
                imageIcon = new ImageIcon("./src/Images/4.jpg");
                g.drawImage(imageIcon.getImage(), x * 30, y * 30, 30, 30, this);
            } else if (model.getField().getValueForgame(x, y) == 5 && model.getField().checkMine(x,y) == false&&
                    model.getField().checkOpenFlag(x, y) == false) {
                imageIcon = new ImageIcon("./src/Images/5.jpg");
                g.drawImage(imageIcon.getImage(), x * 30, y * 30, 30, 30, this);
            } else if (model.getField().checkOpenFlag(x, y) == true && model.getField().checkOpen(x,y) == true) {
                imageIcon = new ImageIcon("./src/Images/f.jpg");
                g.drawImage(imageIcon.getImage(), x * 30, y * 30, 30, 30, this);
            } else if (model.getField().checkMine(x, y) == true) {
                imageIcon = new ImageIcon("./src/Images/b.jpg");
                g.drawImage(imageIcon.getImage(), x * 30, y * 30, 30, 30, this);
            }
        }
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int i = 0; i < sizeCanvas; i++) {
                    for (int j = 0; j < sizeCanvas; j++) {
                        if (model.getField().checkOpen(i, j) == false && model.getField().checkFlag(i, j) == false)
                            g.drawRect(i * 30, j * 30, 30, 30);
                        else {
                            setIm(g, i, j);
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
                if (model.getState() == false) {
                    model.setField(model.getField().checkMines(), x, y);
                    timeLabel = new TimerLabel();
                    add(timeLabel,BorderLayout.SOUTH);
                    timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
                }
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (model.getField().checkMine(x, y) == true && model.getField().checkOpenFlag(x,y) == false
                    || model.getField().youWon()) {
                        label.setText(getessage(x, y));
                        model.getField().openMines();
                        timeLabel.stopTimer();
                        panel.repaint();
                    } else {
                        model.getField().openCell(x, y);
                        panel.repaint();
                    }
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    model.getField().inverseFlag(x, y);
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
            return "Взрыв";
        } else {
            return "Еще остались бомбы";
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
