package com.example.graphsoduku.domain

import java.lang.Exception

interface ISettingsStorage {

    suspend fun getSettings(): SettingsStorageResult
    suspend fun updateSettings(settings: Settings): SettingsStorageResult
}

// result wrapper
// create a restricted set of types
// it allows us to return an object
// this object is capable of representing multiple states
// in success or error we are going to represent that using an object
sealed class SettingsStorageResult {

    data class onSuccess(
        val settings: Settings
    ) : SettingsStorageResult()

    data class onError(
        val exception: Exception
    ) : SettingsStorageResult()

    // for example when you only need to return an unit use an object
    //object onComplete
}