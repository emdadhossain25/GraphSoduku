package com.example.graphsoduku.domain

// data model
// data keyword helps us generate some helper methods: equals, hashcode or copy
data class Settings(
    val difficulty: Difficulty,
    val boundary: Int

)
