package JasonOOP.Functionality;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.text.DecimalFormat;
import java.util.concurrent.*;

public class LiveHourCounter {
    private long secondsElapsed = 0; // Counter for elapsed time in seconds
    private ScheduledExecutorService scheduler;

    // Constructor
    public LiveHourCounter() {
        // Initialize the ScheduledExecutorService
        scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    // Method to start the timer
    public void startTimer(Label timeLabel, Label secondsLabel) {
        // Schedule the task to run every second
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                updateTime(timeLabel, secondsLabel);
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    // Method to update both the time (HH:mm:ss) and seconds
    private void updateTime(Label timeLabel, Label secondsLabel) {
        // Increment the elapsed time in seconds
        secondsElapsed++;

        // Convert elapsed time to hours, minutes, and seconds for HH:mm:ss format
        long hours = secondsElapsed / 3600;
        long minutes = (secondsElapsed % 3600) / 60;
        long seconds = secondsElapsed % 60;

        // Format the time as HH:mm:ss
        String timeFormatted = String.format("%02d:%02d:%02d", hours, minutes, seconds);

        // Format the seconds counter
        String secondsFormatted = String.format("%d", secondsElapsed);
        DecimalFormat df = new DecimalFormat("#0.00");

        // Update the labels with the formatted time on the JavaFX Application Thread
        Platform.runLater(() -> {
            timeLabel.setText(timeFormatted); // Update HH:mm:ss
            secondsLabel.setText("Hourly Rate: " + df.format(Integer.parseInt(secondsFormatted) * 0.03) + "$"); // Update total seconds
        });
    }
}