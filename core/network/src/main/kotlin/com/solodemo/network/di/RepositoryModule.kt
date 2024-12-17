package com.solodemo.network.di

import com.solodemo.network.data.repository.EatsNetworkRepositoryImpl
import com.solodemo.network.domain.repository.EatsNetworkRepository
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
    abstract fun bindEatsNetworkRepository(animeRepositoryImpl: EatsNetworkRepositoryImpl): EatsNetworkRepository
}
