package com.solodemo.database.di

import android.content.Context
import androidx.room.Room
import com.solodemo.database.data.local.CartDao
import com.solodemo.database.data.local.SoloEatsDatabase
import com.solodemo.database.domain.repository.EatsCartRepository
import com.solodemo.database.domain.usecase.CartUseCases
import com.solodemo.database.domain.usecase.cart.DeleteAllCartItem
import com.solodemo.database.domain.usecase.cart.DeleteCartItemById
import com.solodemo.database.domain.usecase.cart.GetCartList
import com.solodemo.database.domain.usecase.cart.UpsertCart
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
    fun provideMenuDao(eatsDatabase: SoloEatsDatabase): CartDao = eatsDatabase.cartDao()

    @Provides
    @Singleton
    fun provideCartUseCases(cartRepository: EatsCartRepository) = CartUseCases(
        getCartList = GetCartList(cartRepository),
        upsertCart = UpsertCart(cartRepository),
        deleteCartItemById = DeleteCartItemById(cartRepository),
        deleteAllCartItem = DeleteAllCartItem(cartRepository),
    )
}
