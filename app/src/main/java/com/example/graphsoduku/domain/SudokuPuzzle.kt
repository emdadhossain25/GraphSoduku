package com.example.graphsoduku.domain

import java.io.Serializable
import java.util.LinkedList

// data model definition: real world object
// for example four or nine tiles boundary
// elapsed time
// difficulty
// graph data structure (itself!!)
// serializable works as file system storage for the data
// LinkedHashMap here is demonstrating adjujency list
data class SudokuPuzzle(
    val boundary: Int,
    val difficulty: Difficulty,
    val graph: LinkedHashMap<Int, LinkedList<SudokuNode>>
    = buildNewSudoku(boundary, difficulty).graph,
    var elapsedTime: Long = 0L
) : Serializable {
    //this method will enable you to get a grab of graph
    fun getValue(): LinkedHashMap<Int, LinkedList<SudokuNode>> = graph
}
