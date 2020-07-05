package com.dynamiccontrols.myob.model

import androidx.lifecycle.LiveData
import com.dynamiccontrols.myob.persistance.CategoriesDao
import javax.inject.Inject

interface GetCategories {
    fun categories() : LiveData<List<Category>>
}

class GetCategoriesImpl @Inject constructor(private val categoriesDao: CategoriesDao) :
    GetCategories {

    override fun categories(): LiveData<List<Category>> {
        return categoriesDao.getCategories()
    }

}
