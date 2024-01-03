package com.solodemo.auth.presenations.module

import android.app.Application
import com.solodemo.auth.presenations.AuthViewModel
import com.solodemo.supabase.di.repository.SupabaseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideAuthViewModule(supaBaseRepository: SupabaseRepositoryImpl, application: Application) : AuthViewModel {
        return AuthViewModel(supaBaseRepository, application)
    }
}