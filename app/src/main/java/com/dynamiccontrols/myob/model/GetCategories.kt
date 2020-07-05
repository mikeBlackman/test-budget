package com.dynamiccontrols.myob.model

import com.dynamiccontrols.myob.persistance.CategoriesDao
import javax.inject.Inject

interface GetCategories {
    fun categories() : List<Category>
}

class GetCategoriesImpl @Inject constructor(private val categoriesDao: CategoriesDao) :
    GetCategories {

    override fun categories(): List<Category> {
        return categoriesDao.getCategories()
    }

}
