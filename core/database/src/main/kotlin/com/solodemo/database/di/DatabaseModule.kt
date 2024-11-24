package com.solodemo.database.di

import android.content.Context
import androidx.room.Room
import com.solodemo.database.data.local.MenuDao
import com.solodemo.database.data.local.SoloEatsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideSoloEatsDatabase(
        @ApplicationContext context: Context,
    ): SoloEatsDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = SoloEatsDatabase::class.java,
            name = "solo_eats_db",
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMenuDao(eatsDatabase: SoloEatsDatabase): MenuDao = eatsDatabase.menuDao()


}
