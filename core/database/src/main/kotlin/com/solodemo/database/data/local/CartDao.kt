package com.solodemo.database.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.solodemo.database.domain.model.Cart
import com.solodemo.database.domain.model.ProductDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * FROM cart")
    fun getCartList(): Flow<List<Cart>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cart: Cart)

    @Query("SELECT * FROM cart WHERE id=:id")
    suspend fun selectCartById(id: Int): Cart?

    @Query("UPDATE cart SET productDetails = :productDetails WHERE id = :id")
    suspend fun updateProductDetailsById(id: Int, productDetails: ProductDetails)

    @Delete
    suspend fun deleteCart(cart: Cart)

    suspend fun upsert(cart: Cart) {
        val existingAnime = selectCartById(cart.id)

        if (existingAnime == null) {
            insertCartItem(cart)
        } else {
            cart.productDetails?.let {
                updateProductDetailsById(cart.id, it)
            }
        }
    }
}
