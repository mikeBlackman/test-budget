package com.dynamiccontrols.myob.ui.categories

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dynamiccontrols.myob.model.Category
import com.dynamiccontrols.myob.model.CategoryManager

class CategoriesViewModel @ViewModelInject constructor(categoryManager: CategoryManager): ViewModel() {

    val categoriesLiveData = MutableLiveData<Set<Category>>(categoryManager.generateCategorySet())

}