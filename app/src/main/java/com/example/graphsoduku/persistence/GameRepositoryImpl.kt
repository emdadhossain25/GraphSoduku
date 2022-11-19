package com.example.graphsoduku.persistence

import com.example.graphsoduku.domain.*

// purpose bridge and decision maker for the backend
// for separate datasources
// Gamestorage and settingsstorage within this repository
class GameRepositoryImpl(
    private val gameStorage: IGameDataStorage,
    private val settingsStorage: ISettingsStorage
) : IGameRespository {
    override suspend fun saveGame(
        elapsedTime: Long,
        onSuccess: (Unit) -> Unit,
        onError: (Exception) -> Unit
    ) {
        when (val getCurrentGameResult = gameStorage.getCurrentGame()) {
            is GameStorageResult.onSuccess -> {
                gameStorage.udpateGame(
                    getCurrentGameResult.currentGame.copy(
                        elapsedTime = elapsedTime
                    )
                )
                onSuccess(Unit)
            }
            is GameStorageResult.onError -> {
                onError(getCurrentGameResult.exception)
            }
        }
    }

    override suspend fun updateGame(
        game: SudokuPuzzle,
        onSuccess: (Unit) -> Unit,
        onError: (Exception) -> Unit
    ) {
        when (val updateGameResult: GameStorageResult = gameStorage.udpateGame(game)) {
            is GameStorageResult.onSuccess -> onSuccess(Unit)
            is GameStorageResult.onError -> onError(updateGameResult.exception)
        }
    }

    override suspend fun updateNode(
        x: Int,
        y: Int,
        color: Int,
        elapsedTime: Long,
        onSuccess: (isComplete: Boolean) -> Unit,
        onError: (Exception) -> Unit
    ) {
        when (val result = gameStorage.udpateNode(x, y, color, elapsedTime)) {
            is GameStorageResult.onSuccess -> onSuccess(
                puzzleComplete(result.currentGame)
            )
            is GameStorageResult.onError -> onError(
                result.exception
            )
        }
    }

    override suspend fun getCurrentGame(
        onSuccess: (currentGame: SudokuPuzzle, isComplete: Boolean) -> Unit,
        onError: (Exception) -> Unit
    ) {
        when (val getCurrentGameResult = gameStorage.getCurrentGame()) {
            is GameStorageResult.onSuccess -> onSuccess(
                getCurrentGameResult.currentGame,
                puzzleIsComple(
                    getCurrentGameResult.currentGame
                )
            )
            is GameStorageResult.onError -> {
                when (val gameSettingsResult = settingsStorage.getSettings()) {
                    is SettingsStorageResult.onSuccess -> {
                        when (val updateGameResult =
                            createAndWriteNewGame(gameSettingsResult.settings)) {
                            is GameStorageResult.onSuccess -> onSuccess(
                                updateGameResult.currenGame,
                                puzzleIsComplete(
                                    updateGameResult.currentGame
                                )
                            )
                            is GameStorageResult.onError -> onError(

                                updateGameResult.exception
                            )
                        }
                    }
                    is SettingsStorageResult.onError -> onError(gameSettingsResult.exception)
                }
            }
        }
    }

    override suspend fun createNewGame(
        settings: Settings,
        onSuccess: (Unit) -> Unit,
        onError: (Exception) -> Unit
    ) {
        when (val updateSettingsResult = settingsStorage.updateSettings(settings)) {
            is SettingsStorageResult.onSuccess -> {
                when (val updateGameResult = createAndWriteNewGame(settings)) {
                    is GameStorageResult.onSuccess -> {
                        onSuccess(Unit)
                    }
                    is GameStorageResult.onError -> onError(updateGameResult.exception)
                }
            }
            is SettingsStorageResult.onError -> {
                onError(updateSettingsResult.exception)
            }
        }
    }

    private suspend fun createAndWriteNewGame(settings: Settings): GameStorageResult {
        return gameStorage.udpateGame(
            SudokuPuzzle(
                settings.boundary,
                settings.difficulty

            )
        )
    }

    override suspend fun getSettings(
        onSuccess: (Settings) -> Unit,
        onError: (Exception) -> Unit
    ) {
        when (val getSettingsResult = settingsStorage.getSettings()) {
            is SettingsStorageResult.onSuccess -> onSuccess(getSettingsResult.settings)
            is SettingsStorageResult.onError -> onError(getSettingsResult.exception)
        }
    }

    override suspend fun updateSettings(
        settings: Settings,
        onSuccess: (Unit) -> Unit,
        onError: (Exception) -> Unit
    ) {
        settingsStorage.updateSettings(settings)
        onSuccess(Unit)
    }
}