package com.example.graphsoduku.persistence

import com.example.graphsoduku.domain.GameStorageResult
import com.example.graphsoduku.domain.IGameDataStorage
import com.example.graphsoduku.domain.SudokuPuzzle
import com.example.graphsoduku.domain.getHash
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

private const val FILE_NAME = "game_state.txt"

class LocalGameStorageImpl(
    fileStorageDirectory: String,
    private val pathToStorageFile: File = File(fileStorageDirectory, FILE_NAME)
) : IGameDataStorage {
    override suspend fun udpateGame(game: SudokuPuzzle): GameStorageResult =
        withContext(Dispatchers.IO) {
            try {
                //helper function
                updateGameData(game)
                //if successful will return the same game object
                GameStorageResult.onSuccess(game)
            } catch (e: Exception) {
                GameStorageResult.onError(e)
            }
        }

    private fun updateGameData(game: SudokuPuzzle) {
        try {
            //we are going to use the input and output stream for our game data
            // be read and write from the file
            // stream -> very long sequence of textual characters
            // serializing is the process of streaming to file

            val fileOutputStream = FileOutputStream(pathToStorageFile)
            val objectOutputStream = ObjectOutputStream(fileOutputStream)
            objectOutputStream.writeObject(game) // this done due to serializable and any type in param
            objectOutputStream.close()


        } catch (e: Exception) {
            //this throw will be caught by the functions calling this
            throw e
        }
    }

    override suspend fun udpateNode(x: Int, y: Int,color:Int, elapsedTime: Long): GameStorageResult =
        withContext(Dispatchers.IO) {
            try {
                val game = getGame()//helper
                game.graph[getHash(x, y)]!!.first.color = color
                game.elapsedTime = elapsedTime
                updateGameData(game)
                GameStorageResult.onSuccess(game)
            } catch (e: Exception) {
                GameStorageResult.onError(e)
            }

        }


    private fun getGame(): SudokuPuzzle {
        try {
            var game: SudokuPuzzle
            val fileInputStream = FileInputStream(pathToStorageFile)
            val objectInputStream = ObjectInputStream(fileInputStream)
            game = objectInputStream.readObject() as SudokuPuzzle
            objectInputStream.close()
            return game
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getCurrentGame(): GameStorageResult
    = withContext(Dispatchers.IO){
        try {
            GameStorageResult.onSuccess(getGame())
        }catch(e:Exception){
            throw  e
        }

    }
}