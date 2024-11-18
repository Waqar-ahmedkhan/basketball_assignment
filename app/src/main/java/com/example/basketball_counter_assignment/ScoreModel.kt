package com.example.basketball_counter_assignment


class ScoreModel {
    var teamAScore: Int = 0
        private set // Encapsulation for safety

    var teamBScore: Int = 0
        private set

    // Increment scores for Team A
    fun addPointsToTeamA(points: Int) {
        teamAScore += points
    }

    // Increment scores for Team B
    fun addPointsToTeamB(points: Int) {
        teamBScore += points
    }

    // Reset both scores
    fun resetScores() {
        teamAScore = 0
        teamBScore = 0
    }

    // Determine the winner
    fun getWinningTeam(): String {
        return when {
            teamAScore > teamBScore -> "Team A is Winning"
            teamBScore > teamAScore -> "Team B is Winning"
            else -> "It's a Tie"
        }
    }
}
