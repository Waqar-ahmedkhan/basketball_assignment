package com.example.basketball_counter_assignment

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var scoreModel: ScoreModel // Instance of the ScoreModel

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Enable edge-to-edge content
        setContentView(R.layout.activity_main)

        // Apply padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize Model
        scoreModel = ScoreModel()

        // Bind Views
        val matchStatus: TextView = findViewById(R.id.match_status)
        val teamAScoreTextView: TextView = findViewById(R.id.team_a_score)
        val teamBScoreTextView: TextView = findViewById(R.id.team_b_score)

        val teamAAdd1Button: Button = findViewById(R.id.team_a_add_1)
        val teamAAdd2Button: Button = findViewById(R.id.team_a_add_2)
        val teamAAdd3Button: Button = findViewById(R.id.team_a_add_3)

        val teamBAdd1Button: Button = findViewById(R.id.team_b_add_1)
        val teamBAdd2Button: Button = findViewById(R.id.team_b_add_2)
        val teamBAdd3Button: Button = findViewById(R.id.team_b_add_3)

        val resetButton: Button = findViewById(R.id.reset_button)

        // Team A Button Clicks
        teamAAdd1Button.setOnClickListener { updateScore(teamAScoreTextView, 1, isTeamA = true) }
        teamAAdd2Button.setOnClickListener { updateScore(teamAScoreTextView, 2, isTeamA = true) }
        teamAAdd3Button.setOnClickListener { updateScore(teamAScoreTextView, 3, isTeamA = true) }

        // Team B Button Clicks
        teamBAdd1Button.setOnClickListener { updateScore(teamBScoreTextView, 1, isTeamA = false) }
        teamBAdd2Button.setOnClickListener { updateScore(teamBScoreTextView, 2, isTeamA = false) }
        teamBAdd3Button.setOnClickListener { updateScore(teamBScoreTextView, 3, isTeamA = false) }

        // Reset Button Click
        resetButton.setOnClickListener {
            scoreModel.resetScores()
            teamAScoreTextView.text = "0"
            teamBScoreTextView.text = "0"
            matchStatus.text = "Let's Play!"
        }
    }

    /**
     * Update the score for the respective team and refresh the UI.
     *
     * @param textView The TextView to update (team's score display).
     * @param points Points to add.
     * @param isTeamA Whether the points are for Team A.
     */
    private fun updateScore(textView: TextView, points: Int, isTeamA: Boolean) {
        if (isTeamA) {
            scoreModel.addPointsToTeamA(points)
            textView.text = scoreModel.teamAScore.toString()
        } else {
            scoreModel.addPointsToTeamB(points)
            textView.text = scoreModel.teamBScore.toString()
        }

        // Update the match status
        val matchStatus: TextView = findViewById(R.id.match_status)
        matchStatus.text = scoreModel.getWinningTeam()
    }
}
