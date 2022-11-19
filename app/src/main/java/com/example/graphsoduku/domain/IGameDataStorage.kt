package com.example.graphsoduku.domain

import java.lang.Exception

interface IGameDataStorage {

    suspend fun udpateGame(game: SudokuPuzzle): GameStorageResult
    suspend fun udpateNode(x: Int, y: Int, elapsedTime: Long): GameStorageResult
    suspend fun getCurrentGame(): GameStorageResult

}


// result wrapper
// create a restricted set of types
// it allows us to return an object
// this object is capable of representing multiple states
// in success or error we are going to represent that using an object
sealed class GameStorageResult {

    data class onSuccess(
        val currentGame: SudokuPuzzle
    ) : GameStorageResult()

    data class onError(
        val exception: Exception
    ) : GameStorageResult()

    // for example when you only need to return an unit use an object
    //object onComplete
}