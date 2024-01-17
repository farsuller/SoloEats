package com.solodemo.main.module

import android.app.Application
import com.solodemo.main.presentations.MainViewModel
import com.solodemo.main.presentations.dashboard.cart.CartViewModel
import com.solodemo.supabase.di.repository.SupabaseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideMainViewModule(supaBaseRepository: SupabaseRepositoryImpl, application: Application) : MainViewModel {
        return MainViewModel(supaBaseRepository, application)
    }

    @Provides
    fun provideCartViewModule(supaBaseRepository: SupabaseRepositoryImpl, application: Application) : CartViewModel {
        return CartViewModel(supaBaseRepository, application)
    }
}