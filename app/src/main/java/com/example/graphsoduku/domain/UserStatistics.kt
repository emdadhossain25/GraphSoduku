package com.example.graphsoduku.domain

// best time / shortest time for solving any difficulty of puzzle
// using long for storing time in milliseconds
data class UserStatistics(
    val fourEasy: Long = 0,
    val fourMedium: Long = 0,
    val fourHard: Long = 0,
    val nineEasy: Long = 0,
    val nineMedium: Long = 0,
    val nineHard: Long = 0,
)
