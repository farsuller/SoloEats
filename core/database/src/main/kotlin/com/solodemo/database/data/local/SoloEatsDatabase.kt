package com.solodemo.database.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.solodemo.database.domain.model.Cart

@Database(entities = [Cart::class], version = 4, exportSchema = false)
@TypeConverters(Converters::class)
abstract class SoloEatsDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}
