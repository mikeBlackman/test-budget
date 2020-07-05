package com.dynamiccontrols.myob.persistance

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dynamiccontrols.myob.model.Category
import com.dynamiccontrols.myob.model.Transaction

@Dao
interface CategoriesDao {

    @Query("SELECT * FROM Category")
    fun getCategories(): LiveData<List<Category>>

    @Insert
    suspend fun insertCategories(vararg transactions: Transaction)

}