package com.example.nixfit.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.nixfit.domain.model.Product


@Database(entities = [Product::class], version = 1)
@TypeConverters(FoodTypeConverter::class)
abstract class FoodDatabase : RoomDatabase() {

    abstract val foodDao: FoodDao

}