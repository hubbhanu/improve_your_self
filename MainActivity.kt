package com.example.sunday

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var isTimerRunning = false
    private var elapsedTime: Long = 0
    private var startTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize buttons and TextView for the timer display
        val startButton = findViewById<Button>(R.id.start_button)
        val stopButton = findViewById<Button>(R.id.stop_button)
        val resetButton = findViewById<Button>(R.id.reset_button)
        val timerDisplay = findViewById<TextView>(R.id.timer_display)

        // Add listeners to buttons
        startButton.setOnClickListener {
            if (!isTimerRunning) {
                startTimer()
            }
        }

        stopButton.setOnClickListener {
            if (isTimerRunning) {
                stopTimer()
            }
        }

        resetButton.setOnClickListener {
            resetTimer()
        }
    }

    private fun startTimer() {
        isTimerRunning = true
        startTime = System.currentTimeMillis() - elapsedTime
        updateTimerDisplay()
    }

    private fun stopTimer() {
        isTimerRunning = false
        elapsedTime = System.currentTimeMillis() - startTime
    }

    private fun resetTimer() {
        isTimerRunning = false
        elapsedTime = 0
        updateTimerDisplay()
    }

    private fun updateTimerDisplay() {
        // Update the timer display based on current time or elapsed time
        val currentTime = if (isTimerRunning) {
            System.currentTimeMillis() - startTime
        } else {
            elapsedTime
        }

        val seconds = (currentTime / 1000 % 60).toInt()
        val minutes = (currentTime / (1000 * 60) % 60).toInt()
        val hours = (currentTime / (1000 * 60 * 60)).toInt()

        // Format time as HH:mm:ss and display it
        val timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds)
        findViewById<TextView>(R.id.timer_display).text = timeString
    }
}
