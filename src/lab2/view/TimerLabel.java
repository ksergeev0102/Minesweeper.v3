package lab2.view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.TimerTask;

public class TimerLabel extends JLabel {
    private Timer timer;

    TimerLabel(ActionListener e) {
        this.timer = new Timer(0, e );
    }

    TimerTask timerTask = new TimerTask() {
        volatile int time;
        Runnable refresher = new Runnable() {
            public void run() {
                TimerLabel.this.setText(String.format("%02d:%02d", time / 60, time % 60));
            }
        };

        public void run() {
            time++;
            SwingUtilities.invokeLater(refresher);
        }
    };

    void stopTimer() {
        this.timer.stop();
    }
}

