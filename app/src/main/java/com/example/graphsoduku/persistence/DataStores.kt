package com.example.graphsoduku.persistence

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.example.graphsoduku.GameSettings
import com.example.graphsoduku.Statistics
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

// serializations means translating JVM file(java,kotlin) into
// Serialization language like JSON,ProtoBuff
internal val Context.settingsDataStore: DataStore<GameSettings> by dataStore(
    fileName = "game_settings.pb",
    serializer = GameSettingsSerializer
)

// it helps us to read and write from inputstreams
// reading from a protocolbuffer file
//
private object GameSettingsSerializer : Serializer<GameSettings> {
    override val defaultValue: GameSettings
        get() = GameSettings.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): GameSettings {
        try {
            return GameSettings.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto", e)
        }
    }

    override suspend fun writeTo(t: GameSettings, output: OutputStream) = t.writeTo(output)

}

internal val Context.statsDataStore: DataStore<Statistics> by dataStore(
    fileName = "user_statistics.pb",
    serializer = StatisticsSerializer
)

private object StatisticsSerializer : Serializer<Statistics> {
    override val defaultValue: Statistics
        get() = Statistics.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): Statistics {
        try {
            return Statistics.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto", e)
        }
    }

    override suspend fun writeTo(t: Statistics, output: OutputStream) = t.writeTo(output)

}