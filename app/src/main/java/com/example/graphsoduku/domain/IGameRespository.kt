package com.example.graphsoduku.domain

//these functions can be thought of as use cases themselves
// example saveGame, updateGame etc
// small to medium scale applications
interface IGameRespository {

    // called from coroutine scopes logic/presnter class
    // for establishing concurrency
    // when the user hits the app
    suspend fun saveGame(
        elapsedTime: Long,
        onSuccess: (Unit) -> Unit,// resume the app successfully does not always return anything
        onError: (Exception) -> Unit,//same goes for the error method
// these function types are reference to those specific funcions and callbacks
    )

    // specialized functions to achieve from users POV
    suspend fun updateGame(
        game: SudokuPuzzle,
        onSuccess: (Unit) -> Unit,
        onError: (Exception) -> Unit
    )

    suspend fun updateNode(
        x: Int,
        y: Int,
        color: Int,
        elapsedTime: Long,
        onSuccess: (isComplete: Boolean) -> Unit, // called with boolean
        onError: (Exception) -> Unit
    )


    suspend fun getCurrentGame(
        onSuccess: (
            currentGame: SudokuPuzzle,
            isComplete: Boolean
        ) -> Unit,
        onError: (Exception) -> Unit
    )

    suspend fun getSettings(
        onSuccess: (Settings) -> Unit,
        onError: (Exception) -> Unit
    )

    suspend fun updateSettings(
        settings: Settings,
        onSuccess: (Settings) -> Unit,
        onError: (Exception) -> Unit
    )
}