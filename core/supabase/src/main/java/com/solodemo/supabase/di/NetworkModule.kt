package com.solodemo.supabase.di

import com.solodemo.supabase.data.MenusDataSource
import com.solodemo.supabase.repository.DefaultMenusRepository
import com.solodemo.supabase.repository.MenusRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
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
        }
    }

    @Provides
    @Singleton
    fun provideUserDataSource(client: SupabaseClient): MenusDataSource {
        return MenusDataSource(client)
    }

    @Provides
    @Singleton
    fun provideMenusRepository(menusDataSource: MenusDataSource) : MenusRepository {
        return DefaultMenusRepository(menusDataSource)
    }

}