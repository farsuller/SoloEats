package com.solodemo.database.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.solodemo.database.domain.model.Menu

@Database(
    entities = [Menu::class],
    version = 1
)
abstract class SoloEatsDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDao

}