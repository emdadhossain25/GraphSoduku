package com.example.graphsoduku.domain

import java.io.Serializable

/* A node in sudoku puzzle is denoted by:
a value or color, which is an integer denoted by the set of all numbers
in the sudoku game
- A list of relative x and y value, where:
- top left = x0,yo (assume 0 based indexing)
-bottom right = xn-1,yn-1, where n is the largest integers in all
allowed numbers
 */
data class SudokuNode(
    val x: Int,
    val y: Int,
    var color: Int = 0,
    var readOnly: Boolean = true // can not edited by the user
): Serializable // this actually writes the data in a file system
 {

    override fun hashCode(): Int {
        return getHash(x, y)
    }
}


// top level outside the brackets of the sudokuNode Class
// hash is generated key or generated value
/*
 for 9x9 and 16x16 larger puzzle boards
 hashcodes will have to unique
 hence x is multiplied by 100
 */
// this hash codes will stored as keys to the hashmap in sudoku puzzle
// hash codes are useful for large sets of hashmaps
internal fun getHash(
    x: Int,
    y: Int
): Int {
    val newX = x * 100
    return "$newX$y".toInt()
}
