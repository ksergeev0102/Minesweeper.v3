package lab2.view;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class TimerLabel extends JLabel {
    private java.util.Timer timer = new Timer();

    TimerLabel() {
        timer.scheduleAtFixedRate(timerTask,0,1000);

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
        this.timer.cancel();
    }
}

