package com.example.graphsoduku.domain

// Enum - Restricted Set of Values Class to define difficulty levels
// Sealed Class - Restricted Set of Types
// benefit - improve the legibility (the quality of being clear enough to read)  of your programs
// we'll be using some values for each of this entries - to dictate difficulty of puzzle

enum class Difficulty(val modifier: Double) {
    EASY(0.50),
    MEDIUM(0.44),
    HARD(0.38)
}