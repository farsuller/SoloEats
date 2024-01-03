package com.solodemo.main.module

import com.solodemo.main.MainViewModel
import com.solodemo.supabase.di.repository.MenusRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideMainViewModule(userRepository: MenusRepositoryImpl) : MainViewModel {
        return MainViewModel(userRepository)
    }

}