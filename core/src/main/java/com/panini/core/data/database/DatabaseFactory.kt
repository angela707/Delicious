package com.panini.core.data.database

import androidx.room.RoomDatabase
import kotlin.reflect.KClass

interface DatabaseFactory {
    fun <T : RoomDatabase> create(
        dbName : String? = null,
        klass: KClass<T>,
    ): T
}