package com.dynamiccontrols.myob.persistance

import androidx.room.Dao
import androidx.room.Query
import com.dynamiccontrols.myob.model.Category

@Dao
interface CategoriesDao {

    @Query("SELECT * FROM Category")
    fun getCategories(): List<Category>

}