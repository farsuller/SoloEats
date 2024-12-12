package com.solodemo.database.di

import com.solodemo.database.data.repository.EatsCartRepositoryImpl
import com.solodemo.database.domain.repository.EatsCartRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindEatsCartRepository(eatsCartRepositoryImpl: EatsCartRepositoryImpl): EatsCartRepository
}
