package com.solodemo.supabase.di

import com.solo.components.Constants
import com.solodemo.supabase.di.data.SupabaseDataSource
import com.solodemo.supabase.di.repository.SupabaseRepositoryImpl
import com.solodemo.supabase.domain.repository.SupabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.googleNativeLogin
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://wanvkegwhowxwkhiejgn.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6IndhbnZrZWd3aG93eHdraGllamduIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDQwNzg4ODAsImV4cCI6MjAxOTY1NDg4MH0.4kaP8VB2iccjoDxb0K3rBrTW2XxdYQBhmO9KC5nK6fg")
        {
            install(Postgrest)
            install(Auth)
            install(ComposeAuth){
                googleNativeLogin(serverClientId = Constants.CLIENT_ID)
            }
        }
    }

    @Provides
    @Singleton
    fun provideSupabaseDataSource(client: SupabaseClient): SupabaseDataSource {
        return SupabaseDataSource(supaBaseClient = client)
    }

    @Provides
    @Singleton
    fun provideMenusRepository(supabaseDataSource: SupabaseDataSource) : SupabaseRepository {
        return SupabaseRepositoryImpl(supabaseDataSource = supabaseDataSource)
    }

}