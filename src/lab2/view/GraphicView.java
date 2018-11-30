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
        sizeCanvas = scanner.nextInt();
        System.out.println("Введите количество мин на поле: ");
        bombs = scanner.nextInt();
        model = new Gamemodel(sizeCanvas,bombs);
        initLabel();
        initPanel();
        initFrame();
    }

    private void initLabel() {
        label = new JLabel("ОТКРОЙТЕ ВСЕ ПУСТЫЕ ЯЧЕЙКИ");
        add(label, BorderLayout.SOUTH);
    }

    public void setIm(Graphics g, int x, int y) {
        ImageIcon imageIcon;
        if (model.getField().checkOpen(x, y)
                || model.getField().checkOpenFlag(x, y)
                || model.getField().checkopenMine(x, y)) {
            if (model.getField().getValueForgame(x, y) == 0
                    && !model.getField().checkMine(x, y) &&
                    !model.getField().checkOpenFlag(x, y)) {
                imageIcon = new ImageIcon("./src/Images/0.jpg");
                g.drawImage(imageIcon.getImage(), x * 30, y * 30, 30, 30, this);
            } else if (model.getField().getValueForgame(x, y) == 1
                    && !model.getField().checkMine(x, y) &&
                    !model.getField().checkOpenFlag(x, y)) {
                imageIcon = new ImageIcon("./src/Images/1.jpg");
                g.drawImage(imageIcon.getImage(), x * 30, y * 30, 30, 30, this);
            } else if (model.getField().getValueForgame(x, y) == 2
                    && !model.getField().checkMine(x, y) &&
                    !model.getField().checkOpenFlag(x, y)) {
                imageIcon = new ImageIcon("./src/Images/2.jpg");
                g.drawImage(imageIcon.getImage(), x * 30, y * 30, 30, 30, this);
            } else if (model.getField().getValueForgame(x, y) == 3
                    && !model.getField().checkMine(x, y) &&
                    !model.getField().checkOpenFlag(x, y)) {
                imageIcon = new ImageIcon("./src/Images/3.jpg");
                g.drawImage(imageIcon.getImage(), x * 30, y * 30, 30, 30, this);
            } else if (model.getField().getValueForgame(x, y) == 4
                    && !model.getField().checkMine(x, y) &&
                    !model.getField().checkOpenFlag(x, y)) {
                imageIcon = new ImageIcon("./src/Images/4.jpg");
                g.drawImage(imageIcon.getImage(), x * 30, y * 30, 30, 30, this);
            } else if (model.getField().getValueForgame(x, y) == 5
                    && !model.getField().checkMine(x, y) &&
                    !model.getField().checkOpenFlag(x, y)) {
                imageIcon = new ImageIcon("./src/Images/5.jpg");
                g.drawImage(imageIcon.getImage(), x * 30, y * 30, 30, 30, this);
            } else if (model.getField().checkOpenFlag(x, y)) {
                imageIcon = new ImageIcon("./src/Images/f.jpg");
                g.drawImage(imageIcon.getImage(), x * 30, y * 30, 30, 30, this);
            } else if (model.getField().checkopenMine(x, y)) {
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
                        if (!model.getField().checkOpen(i, j)
                                && !model.getField().checkOpenFlag(i, j)
                                && !model.getField().checkopenMine(i, j))
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
                if (!model.getState()) {
                    model.setField(model.getField().checkMines(), x, y);
                    timeLabel = new TimerLabel();
                    add(timeLabel, BorderLayout.SOUTH);
                    timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                }
                if (model.getField().youWon()) {
                    timeLabel.stopTimer();
                    label.setText(getessage(x, y));
                    panel.repaint();
                } else {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        if (model.getField().checkMine(x, y) &&
                                !model.getField().checkOpenFlag(x, y) &&
                                !model.getField().youWon()) {
                            model.getField().openMines();
                            label.setText(getessage(x, y));
                            timeLabel.stopTimer();
                            panel.repaint();
                        } else if (!model.getField().checkMine(x, y)
                                && !model.getField().checkOpenFlag(x, y)
                                && !model.getField().youWon()) {
                            model.getField().openCell(x, y);
                            label.setText(getessage(x, y));
                            panel.repaint();
                            if (model.getField().youWon()) {
                                label.setText(getessage(x, y));
                                timeLabel.stopTimer();
                                panel.repaint();
                            }
                        }
                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                        model.getField().inverseFlag(x, y);
                        panel.repaint();
                    }
                }
            }
        });
        panel.setPreferredSize(new Dimension(30 * sizeCanvas, 30 * sizeCanvas));
        add(panel);

    }

    private String getessage(int x, int y) {
        if (model.getField().youWon()) {
            return "ВЫ ВЫИГРАЛИ";
        } else if (model.getField().checkMine(x, y)) {
            return "ВЗРЫВ";
        } else {
            return "ЕЩЕ ОСТАЛИСЬ БОМБЫ";
        }
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("САПЕР");
        setResizable(false);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }
}
