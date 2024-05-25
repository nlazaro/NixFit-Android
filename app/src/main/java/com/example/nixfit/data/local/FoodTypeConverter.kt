package com.example.nixfit.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.nixfit.domain.model.Nutriments

@ProvidedTypeConverter
class FoodTypeConverter {
    @TypeConverter
    fun foodToString(food: Nutriments): String {
        return "${food.energy},${food.protein},${food.fat},${food.carbohydrates},${food.sugar},${food.sodium}"
    }

    @TypeConverter
    fun stringToFood(food: String): Nutriments{
        return food.split(",").let{sourceArray ->
            Nutriments(
                energy = sourceArray[0],
                protein = sourceArray[1],
                fat = sourceArray[2],
                carbohydrates = sourceArray[3],
                sugar = sourceArray[4],
                sodium = sourceArray[5])
        }
    }
}