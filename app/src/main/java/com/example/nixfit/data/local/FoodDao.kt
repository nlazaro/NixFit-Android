package com.example.nixfit.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nixfit.domain.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(food: Product)

    @Delete
    suspend fun delete(food: Product)

    @Query("SELECT * FROM Product")
    fun getAllFoodEntries(): Flow<List<Product>>
}