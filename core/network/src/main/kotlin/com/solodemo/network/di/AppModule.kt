package com.solodemo.network.di

import com.solodemo.network.BuildConfig
import com.solodemo.network.data.remote.EatsApi
import com.solodemo.network.domain.repository.EatsNetworkRepository
import com.solodemo.network.domain.usecase.EatsUseCases
import com.solodemo.network.domain.usecase.GetCoupons
import com.solodemo.network.domain.usecase.GetMenus
import com.solodemo.network.domain.usecase.GetProducts
import com.solodemo.network.domain.usecase.GetReviews
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideKtorClient(): HttpClient = HttpClient(CIO) {
        install(Logging) {
            level = LogLevel.ALL
            logger = Logger.DEFAULT
        }

        install(ContentNegotiation) {
            json()
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 20_000
            connectTimeoutMillis = 20_000
            socketTimeoutMillis = 20_000
        }

        defaultRequest {
            url(BuildConfig.BASE_URL)
        }
    }

    @Provides
    @Singleton
    fun provideEatsApi(client: HttpClient): EatsApi = EatsApi(client)

    @Provides
    @Singleton
    fun provideEatsUseCases(repository: EatsNetworkRepository): EatsUseCases =
        EatsUseCases(
            getMenus = GetMenus(repository),
            getReviews = GetReviews(repository),
            getCoupons = GetCoupons(repository),
            getProducts = GetProducts(repository),
        )
}
