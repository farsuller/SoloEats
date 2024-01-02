package com.solodemo.main.module

import com.solodemo.main.MainViewModel
import com.solodemo.supabase.repository.DefaultMenusRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ViewModelModule {

    @Provides
    fun provideMainViewModule(userRepository: DefaultMenusRepository) : MainViewModel {
        return MainViewModel(userRepository)
    }

}