package com.panini.core.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.reflect.KClass

class DatabaseFactoryImpl (private val context: Context) : DatabaseFactory {
    override fun <T : RoomDatabase> create(
        dbName: String?,
        klass: KClass<T>
    ): T {
        requireNotNull(dbName) { "Database name must not be null" }
        require(dbName.isNotEmpty() && dbName.isNotBlank()) { "Database name must not be empty or blank" }
        val builder = Room.databaseBuilder(
            context, klass.java,
            dbName
        )
        return builder.build()
    }
}